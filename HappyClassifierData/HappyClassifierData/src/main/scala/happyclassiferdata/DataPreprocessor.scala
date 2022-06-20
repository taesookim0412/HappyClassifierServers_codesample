package happyclassiferdata

import com.happyclassifier.happyclassiferstore.models.Sentence
import org.apache.spark
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object DataPreprocessor {
  def main(args: Array[String]): Unit ={
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("HappyClassifier")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.textFile("data/emotions/test.txt")

    val data = rdd.map(preprocess_data)

    val sentence = new Sentence("phrase");
    println(sentence.getPhrase);
  }

  def preprocess_data(line: String): (String, Double) ={


  }
}
