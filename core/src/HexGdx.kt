import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

class HexGdx : ApplicationAdapter() {
  private lateinit var dropImage: Texture
  private lateinit var bucketImage: Texture
  private lateinit var dropSound: Sound
  private lateinit var rainMusic: Music
  private lateinit var batch: SpriteBatch
  private lateinit var bucket: Rectangle
  private lateinit var touchPos: Vector3
  // The camera ensures we can render using our target resolution of 800x480
  //    pixels no matter what the screen resolution is.
  private lateinit var camera: OrthographicCamera

  override fun create() {
    batch = SpriteBatch()

    dropImage = Texture(Gdx.files.internal("droplet.png"))
    bucketImage = Texture(Gdx.files.internal("bucket.png"))
    dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"))
    rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"))

    // start the playback of the background music immediately
//    rainMusic.setLooping(true)
//    rainMusic.play()

    camera = OrthographicCamera()
    camera.setToOrtho(false, 800f, 480f)

    bucket = Rectangle()
    bucket.x = 800f/2f - 64f/2f
    bucket.y = 20f
    bucket.width = 64f
    bucket.height = 64f

    touchPos = Vector3()
  }

  override fun render() {
    // clear the screen & paint it red
    Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    // generally good practice to update camera once per frame
    camera.update()

    batch.setProjectionMatrix(camera.combined)
    batch.begin()
    batch.draw(bucketImage, bucket.x, bucket.y)
    batch.end()

    if (Gdx.input.isTouched()) {
      touchPos.set(Gdx.input.getX().toFloat(),
                   Gdx.input.getY().toFloat(),
                   0f)
      camera.unproject(touchPos)
      bucket.x = touchPos.x - 64f/2f
    }

    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
      // getDeltaTime returns the time passed between the last and the current
      //    frame in seconds
      bucket.x -= 200 * Gdx.graphics.getDeltaTime()
    }
    if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
      bucket.x += 200 * Gdx.graphics.getDeltaTime()
    }

    if (bucket.x < 0f) bucket.x = 0f
    if (bucket.x > 800f-64f) bucket.x = 800f-64f
  }

  override fun dispose() {
    batch.dispose()
  }
}

