import javafx.stage.Stage
import javafx.stage.StageStyle
import tornadofx.*

class Aplicacion: App()
{
    override val primaryView = Calculadora::class


    override fun start(stage: Stage) {

        importStylesheet("/estilos.css")
        stage.initStyle(StageStyle.UNDECORATED)
        stage.initStyle(StageStyle.TRANSPARENT)
        stage.isResizable = false
        super.start(stage)

        //Hace una llamado a la misma clase, ya que asi se acceden a los companion object
        Aplicacion.stage=stage

    }

    companion object {
        lateinit var stage: Stage
            private set //Evita la escritura fuera de esta clase
    }
}