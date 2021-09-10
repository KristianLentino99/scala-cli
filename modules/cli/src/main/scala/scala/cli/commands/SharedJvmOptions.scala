package scala.cli.commands

import caseapp._

import scala.build.Os
import scala.build.options.JavaOptions

// format: off
final case class SharedJvmOptions(

  @Group("Java")
  @HelpMessage("Set Java home")
  @ValueDescription("path")
    javaHome: Option[String] = None,

  @Group("Java")
  @HelpMessage("Use a specific JVM, such as 14, adopt:11, or graalvm:21, or system")
  @ValueDescription("jvm-name")
  @Name("j")
    jvm: Option[String] = None,
  @Group("Java")
  @HelpMessage("JVM index URL")
  @ValueDescription("url")
    jvmIndex: Option[String] = None,
  @Group("Java")
  @HelpMessage("Operating system to use when looking up in the JVM index")
  @ValueDescription("linux|linux-musl|darwin|windows|…")
    jvmIndexOs: Option[String] = None,
  @Group("Java")
  @HelpMessage("CPU architecture to use when looking up in the JVM index")
  @ValueDescription("amd64|arm64|arm|…")
    jvmIndexArch: Option[String] = None
) {
  // format: on

  def javaOptions = JavaOptions(
    javaHomeOpt = javaHome.filter(_.nonEmpty).map(os.Path(_, Os.pwd)),
    jvmIdOpt = jvm.filter(_.nonEmpty),
    jvmIndexOpt = jvmIndex.filter(_.nonEmpty),
    jvmIndexOs = jvmIndexOs.map(_.trim).filter(_.nonEmpty),
    jvmIndexArch = jvmIndexArch.map(_.trim).filter(_.nonEmpty)
  )

}

object SharedJvmOptions {
  implicit val parser = Parser[SharedJvmOptions]
  implicit val help   = Help[SharedJvmOptions]
}
