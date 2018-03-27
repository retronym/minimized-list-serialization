## Classloaders vs the Serialization Proxy pattern

Demo of https://github.com/scala/bug/issues/9237

```
[info] Running issue.Test
--------------------------------------------------------------------------------
URLClassLoader with NativeCopyLoader with RawResources(
  urls = Vector(/var/folders/b7/xcc2k0ln6ldcv247ffpy2d1w0000gp/T/sbt_763808b7/job-1/target/f848e50f/minimized-list-serialization_2.11-0.1.0-SNAPSHOT-tests.jar, /var/folders/b7/xcc2k0ln6ldcv247ffpy2d1w0000gp/T/sbt_763808b7/target/76105cdb/minimized-list-serialization_2.11-0.1.0-SNAPSHOT.jar, /var/folders/b7/xcc2k0ln6ldcv247ffpy2d1w0000gp/T/sbt_763808b7/target/be3457b4/scala-library-2.11.6.jar),
  parent = java.net.URLClassLoader@1d456062,
  resourceMap = Set(app.class.path, boot.class.path),
  nativeTemp = /var/folders/b7/xcc2k0ln6ldcv247ffpy2d1w0000gp/T/sbt_bcc270a1
) (class sbt.internal.inc.classpath.ClasspathUtilities$$anon$1)
file:/var/folders/b7/xcc2k0ln6ldcv247ffpy2d1w0000gp/T/sbt_763808b7/job-1/target/f848e50f/minimized-list-serialization_2.11-0.1.0-SNAPSHOT-tests.jar
file:/var/folders/b7/xcc2k0ln6ldcv247ffpy2d1w0000gp/T/sbt_763808b7/target/76105cdb/minimized-list-serialization_2.11-0.1.0-SNAPSHOT.jar
file:/var/folders/b7/xcc2k0ln6ldcv247ffpy2d1w0000gp/T/sbt_763808b7/target/be3457b4/scala-library-2.11.6.jar

java.net.URLClassLoader@1d456062 (class java.net.URLClassLoader)
file:/Users/jz/.ivy2/cache/org.scala-lang/scala-library/jars/scala-library-2.11.6.jar

xsbt.boot.BootFilteredLoader@1d71006f (class xsbt.boot.BootFilteredLoader)


sun.misc.Launcher$AppClassLoader@75b84c92 (class sun.misc.Launcher$AppClassLoader)
file:/usr/local/Cellar/sbt/1.1.1/libexec/bin/sbt-launch.jar

sun.misc.Launcher$ExtClassLoader@2173f6d9 (class sun.misc.Launcher$ExtClassLoader)
file:/Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/jre/lib/ext/cldrdata.jar
file:/Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/jre/lib/ext/dnsns.jar
file:/Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/jre/lib/ext/jaccess.jar
file:/Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/jre/lib/ext/jfxrt.jar
file:/Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/jre/lib/ext/localedata.jar
file:/Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/jre/lib/ext/nashorn.jar
file:/Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/jre/lib/ext/sunec.jar
file:/Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar
file:/Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar
file:/Library/Java/JavaVirtualMachines/jdk1.8.0_162.jdk/Contents/Home/jre/lib/ext/zipfs.jar
file:/System/Library/Java/Extensions/MRJToolkit.jar
--------------------------------------------------------------------------------
{a=Meh(ay), b=Meh(bee)} : Success(true)
List(Meh(2345)) : Failure(java.lang.ClassNotFoundException: issue.Meh)
[success] Total time: 12 s, completed 27/03/2018 2:20:27 PM
```