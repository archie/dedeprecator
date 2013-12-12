package dedep

import java.io.File
import java.io.FileWriter
import java.io.BufferedWriter

object DeDeprecator {

  def fix(line: String): String = {
    if (line == "import org.scalatest.matchers.MustMatchers") 
      "import org.scalatest.Matchers"
    else if (line.contains("with MustMatchers"))
      line.replaceFirst("with MustMatchers", "with Matchers")
    else if (line.contains("with ShouldMatchers"))
      line.replaceFirst("with ShouldMatchers", "with Matchers")
    else if (line.contains(" must ") && !line.trim().startsWith("//") && !line.trim().startsWith("*")) { 
    	val upd = line.replaceAll(" must ", " should ") 
    	if (upd.contains(" be === ") && !upd.contains(" === (") && !upd.contains("assert")) {
    		val splitted = upd.split(" be === ")
    		splitted(0) + " be (" + splitted(1) + ")"
    	} else upd
    }
    else 
      line
  }
  
  def processFile(fileName: String, tmpName: String) = {
  	val code = for (raw <- io.Source.fromFile(fileName).getLines) yield fix(raw)
  	val outputFile = new File(tmpName)
  	val writer = new BufferedWriter(new FileWriter(outputFile))
  	code.foreach { line => 
  	  writer.write(line)
  	  writer.newLine()
  	}
  	writer.flush()
  	writer.close()
  	
  	renameToOriginalName(tmpName, fileName)
  }
  
  def renameToOriginalName(tmpName: String, orig: String) = { 
    val origFile = new File(orig)
    val tmpFile = new File(tmpName)
    if (tmpFile.renameTo(origFile))
      println(s"processed $orig")
    else 
      println(s"failed $orig")
  }

  def main(args: Array[String]): Unit = {
    
    args.foreach { fileName =>
      if (fileName.endsWith(".scala")) {
      	val tmpName = fileName+"-tmp"
   			processFile(fileName, tmpName)
      }
      
    }
  }
}