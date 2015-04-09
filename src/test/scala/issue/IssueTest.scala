package issue

import java.io.{ObjectOutputStream, ObjectInputStream, ByteArrayOutputStream, ByteArrayInputStream}
import java.net.URLClassLoader
import java.util
import java.util.Comparator

object Test {
  def main(args: Array[String]): Unit = {
    classloaderDiagnostic()

    val treeMap = new util.TreeMap[String, Meh]()
    treeMap.put("a", new Meh("ay"))
    treeMap.put("b", new Meh("bee"))
    test(treeMap)

    val list = List(Meh("2345"))
    test(List(Meh("2345")))
  }
  def test(obj: Any) = {
    val result = scala.util.Try {
      val arr = serialize(obj)
      val obj2 = deserialize[Any](arr)
      obj == obj2
    }
    println(s"$obj : $result")
  }

  def serialize[A](obj: A): Array[Byte] = {
    val o = new ByteArrayOutputStream()
    val os = new ObjectOutputStream(o)
    os.writeObject(obj)
    o.toByteArray()
  }

  def deserialize[A](bytes: Array[Byte]): A = {
    val s = new ByteArrayInputStream(bytes)
    val is = new ObjectInputStream(s)
    is.readObject().asInstanceOf[A]
  }

  def classloaderDiagnostic(): Unit = {
    def classLoaderChain(loader: ClassLoader) = collection.Iterator.iterate(loader)(_.getParent).takeWhile(_ != null)
    def showLoader(cl: ClassLoader) = {
      cl.toString + " (" + cl.getClass + ")\n" + {
        cl match {
          case url: URLClassLoader => url.getURLs.mkString("\n")
          case _ => ""
        }
      }
    }
    println(classLoaderChain(classOf[Meh].getClassLoader).toList.map(showLoader).mkString("-" * 80 + "\n", "\n\n", "\n" + ("-" * 80)))
  }
}
