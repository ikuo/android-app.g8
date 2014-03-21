package $package$

import _root_.android.app.Activity
import _root_.android.os.Bundle

class $main_activity$ extends Activity with TypedActivity { activity =>
  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.main)

    findView(TR.textview).setText("Loading ...")
    thread.start
  }

  private val thread: Thread = new Thread(
    new Runnable {
      override def run {
        activity.updateText(testMessage)
      }
    }
  )

  private lazy val testMessage = {
    // Set dev_id and md5password from ~/work/garapon4s/garapon4s.properties
    val client = new com.github.ikuo.garapon4s.TvClient("@@dev_id@@")
    val session =
      client.newSession(
        "matzun",
        "@@md5password@@"
      )
    session.gtvsession
  }

  private def updateText(text: String) {
    runOnUiThread(new Runnable {
      override def run {
        findView(TR.textview).setText(text)
      }
    })
  }
}
