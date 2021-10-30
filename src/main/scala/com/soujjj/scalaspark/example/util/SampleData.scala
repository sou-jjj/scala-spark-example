package com.soujjj.scalaspark.example.util

object SampleData {
  val absolutePath = "src/main/resources/sample"

  def getPath(fileName: String): String = s"$absolutePath/$fileName"
}
