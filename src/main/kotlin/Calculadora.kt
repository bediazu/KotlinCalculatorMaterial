import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.VBox
import javafx.stage.Stage
import tornadofx.*


class Calculadora: View()
{
    override val root: VBox by fxml()

    @FXML
    lateinit var display: Label

    @FXML
    lateinit var btnExit: Button

    private var xOffset: Double = 0.0
    private var yOffset: Double = 0.0


    init{
        title= "Calculadora"

        root.addEventFilter(MouseEvent.MOUSE_PRESSED) {
            xOffset = Aplicacion.stage.x - it.sceneX
            yOffset = Aplicacion.stage.y - it.sceneY
        }

        root.addEventFilter(MouseEvent.MOUSE_DRAGGED){
            Aplicacion.stage.x = it.sceneX + xOffset
            Aplicacion.stage.y = it.sceneY + yOffset
        }


        root.lookupAll(".button").forEach{b->
            b.setOnMouseClicked {
                operador((b as Button).text)
            }
        }

        btnExit.setOnAction {
            val s = btnExit.scene.window as Stage
            s.close()
        }

        root.addEventFilter(KeyEvent.KEY_TYPED)
        {
            operador(it.character.toUpperCase().replace("\r","="))
        }
    }

    var state: Operador = Operador.sumar(0)

    fun onAction(fn: Operador)
    {
        state = fn
        display.text = ""
    }

    val valorMostrado: Long
        get() = when (display.text){
            "" -> 0
            else  -> display.text.toLong()
        }

    private fun operador(x: String){
        if(Regex("[0-9]").matches(x)){
            display.text += x
        }else {
            when(x){
                "+" -> onAction(Operador.sumar(valorMostrado))
                "-" -> onAction(Operador.restar(valorMostrado))
                "/" -> onAction(Operador.dividir(valorMostrado))
                "%" -> { onAction(Operador.sumar(valorMostrado / 100)) ; operador("=")}
                "x" -> onAction(Operador.multiplicar(valorMostrado))
                "C" -> onAction(Operador.sumar(0))
                "+/-" -> { onAction(Operador.sumar(-1 * valorMostrado)) ; operador("=")}
                "=" -> display.text = state.calcular(valorMostrado).toString()
            }
        }
    }
}