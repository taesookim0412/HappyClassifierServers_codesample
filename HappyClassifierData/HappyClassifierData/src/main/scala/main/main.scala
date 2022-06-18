import com.happyclassifier.happyclassiferstore.models.Sentence
import org.apache.spark
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object main {
  def main(args: Array[String]): Unit ={
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("HappyClassifier")
    val sc = new SparkContext(sparkConf);

    sc.parallelize(Array(1,2,3,4,5))
    System.out.println("didn't break");

    val sentence = new Sentence("phrase");
    println(sentence.getPhrase());
  }
}
