package uz.evkalipt.eightmodullesson2ndk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import net.objecthunter.exp4j.ExpressionBuilder
import uz.evkalipt.eightmodullesson2ndk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    var operation: String? = "+"

    override fun onCreate(savedInstanceState: Bundle?) {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.Theme_Dark)
        }else{
            setTheme(R.style.Theme_Light)
        }

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.onOff.setOnCheckedChangeListener { _, p1 ->
            if (p1) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }


    }

    private fun operations(a:Int){
        try {
            var str01 = binding.tvBottom.text.toString()
            if (str01.length>2 && (str01.substring(binding.tvBottom.text.toString().length-2, binding.tvBottom.text.toString().length-1) == "+" ||
                        str01.substring(binding.tvBottom.text.toString().length-2, binding.tvBottom.text.toString().length-1) == "-" ||
                        str01.substring(binding.tvBottom.text.toString().length-2, binding.tvBottom.text.toString().length-1) == "/" ||
                        str01.substring(binding.tvBottom.text.toString().length-2, binding.tvBottom.text.toString().length-1) == "*" ||
                        str01.substring(binding.tvBottom.text.toString().length-2, binding.tvBottom.text.toString().length-1) == "%") &&
                str01.substring(binding.tvBottom.text.toString().length-1) == "0"){
                binding.tvBottom.text = str01.substring(0, str01.length-1)+a
            }else{
                binding.tvBottom.text = "$str01$a"
            }
            var text = binding.tvBottom.text.toString()
            var expression = ExpressionBuilder(text).build()
            var res = expression.evaluate()
            var point0 = res.toString().substringAfter(".")
            if (!(binding.tvBottom.text.toString().contains("+") ||
                        binding.tvBottom.text.toString().contains("-") ||
                        binding.tvBottom.text.toString().contains("/") ||
                        binding.tvBottom.text.toString().contains("*") ||
                        binding.tvBottom.text.toString().contains("%"))) {
                binding.tvTop.text = ""
            } else if (point0 == "0") {
                binding.tvTop.text = res.toString().substring(0, res.toString().length - 2)
            } else {
                binding.tvTop.text = res.toString()
            }
        }catch (e: ArithmeticException){
            Toast.makeText(this, "Nolga bo'lish mumkin emas", Toast.LENGTH_SHORT).show()
        }
    }

    private fun originalOperations(s:String){
        if (binding.tvBottom.text!!.isNotEmpty()) {
            var operationOne: String = binding.tvBottom.text.toString()
            if (operationOne.substring(operationOne.length - 1) == "+" ||
                operationOne.substring(operationOne.length - 1) == "/" ||
                operationOne.substring(operationOne.length - 1) == "-" ||
                operationOne.substring(operationOne.length - 1) == "*" ||
                operationOne.substring(operationOne.length - 1) == "%") {
                var operation1 = operationOne.substring(0, operationOne.length - 1) + s
                binding.tvBottom.text = operation1
            } else {
                binding.tvBottom.text = "${binding.tvBottom?.text}$s"
            }
            operation = s
        } else {
            binding.tvBottom.text = ""
        }
        binding.tvTop.text = ""
    }

    fun onClick(view: View) {
        if (binding.tvBottom.text.toString() == "0") {
            binding.tvBottom.text = ""
        }

        var id = view.id
        when (id) {
            R.id.clearAll -> {
                binding.tvBottom.text = ""
                binding.tvTop.text = ""
            }
            R.id.clearOne -> {
                try {
                    if (binding.tvBottom.text.toString() !="") {
                        if (binding.tvBottom.text.toString().length==2 && binding.tvBottom.text.toString().substring(0,1) == "-"){
                            binding.tvBottom.text = ""
                            binding.tvTop.text = ""
                        }else {
                            binding.tvBottom.text = binding.tvBottom.text.toString()
                                .substring(0, binding.tvBottom.text.toString().length - 1)
                            var simbol: String = binding.tvBottom.text.toString()

                            if (simbol.isEmpty() || !(simbol.contains("+") || simbol.contains("-") || simbol.contains(
                                    "/"
                                ) || simbol.contains("*") || simbol.contains("%"))
                                || simbol.substring(simbol.length - 1) == "+" || simbol.substring(simbol.length - 1) == "-" ||
                                simbol.substring(simbol.length - 1) == "*" || simbol.substring(simbol.length - 1) == "/" || simbol.substring(
                                    simbol.length - 1
                                ) == "%"
                            ) {
                                binding.tvTop.text = ""
                            } else {
                                var text = binding.tvBottom.text.toString()
                                var expression = ExpressionBuilder(text).build()
                                var res = expression.evaluate()
                                var zero0: String = res.toString().substringAfter(".")
                                if (zero0 == "0") {
                                    binding.tvTop.text = res.toString().substring(0, res.toString().length - 2)
                                } else {
                                    binding.tvTop.text = res.toString()
                                }
                            }
                        }

                    } else {
                        binding.tvBottom.text = ""
                        binding.tvTop.text = ""
                    }
                }catch (e: ArithmeticException) {
                    Toast.makeText(this, "Nolga bo'lish mumkin emas", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.seven -> {
                operations(7)
            }
            R.id.eight -> {
                operations(8)
            }
            R.id.nine -> {
                operations(9)
            }
            R.id.four -> {
                operations(4)
            }
            R.id.five -> {
                operations(5)
            }
            R.id.six -> {
                operations(6)
            }
            R.id.one -> {
                operations(1)
            }
            R.id.two -> {
                operations(2)
            }
            R.id.three -> {
                operations(3)
            }
            R.id.percent -> {
                originalOperations("%")
            }
            R.id.add -> {
                originalOperations("+")
            }
            R.id.sub -> {
                originalOperations("-")
            }
            R.id.mult -> {
                originalOperations("*")
            }
            R.id.div -> {
                originalOperations("/")
            }
            R.id.equality -> {
                if (binding.tvBottom.text!!.isEmpty() && binding.tvTop.text!!.isEmpty()){
                    binding.tvBottom.text = ""
                }else if (binding.tvBottom.text!!.isNotEmpty() && binding.tvTop.text!!.isEmpty()){
                    binding.tvTop.text = ""
                }else if (binding.tvTop.text!!.isNotEmpty()){
                    binding.tvBottom.text = binding.tvTop.text
                    binding.tvTop.text = ""
                }
            }
            R.id.zero -> {
                try {
                    var str0 = binding.tvBottom.text.toString()
                    if (binding.tvBottom.text.toString() == "0" || binding.tvBottom.text!!.isEmpty()) {
                        binding.tvBottom.setText("0")
                    } else if (binding.tvBottom.text.toString().length>1 && (str0.substring(
                            binding.tvBottom.text.toString().length - 2,
                            binding.tvBottom.text.toString().length - 1
                        ) == "+" ||
                                str0.substring(
                                    binding.tvBottom.text.toString().length - 2,
                                    binding.tvBottom.text.toString().length - 1
                                ) == "-" ||
                                str0.substring(
                                    binding.tvBottom.text.toString().length - 2,
                                    binding.tvBottom.text.toString().length - 1
                                ) == "/" ||
                                str0.substring(
                                    binding.tvBottom.text.toString().length - 2,
                                    binding.tvBottom.text.toString().length - 1
                                ) == "*" ||
                                str0.substring(
                                    binding.tvBottom.text.toString().length - 2,
                                    binding.tvBottom.text.toString().length - 1
                                ) == "%") &&
                        str0.substring(binding.tvBottom.text.toString().length - 1) == "0"
                    ) {
                        binding.tvBottom.text = "${binding.tvBottom.text}"
                        var text = binding.tvBottom.text.toString()
                        var expression = ExpressionBuilder(text).build()
                        var res = expression.evaluate()
                        var zero0: String = res.toString().substringAfter(".")
                        if (zero0 == "0") {
                            binding.tvTop.text = res.toString().substring(0, res.toString().length - 2)
                        } else {
                            binding.tvTop.text = res.toString()
                        }
                    } else {
                        binding.tvBottom.text = "${binding.tvBottom.text}0"
                        var text = binding.tvBottom.text.toString()
                        var expression = ExpressionBuilder(text).build()
                        var res = expression.evaluate()
                        var zero0: String = res.toString().substringAfter(".")
                        if (zero0 == "0") {
                            binding.tvTop.text = res.toString().substring(0, res.toString().length - 2)
                        } else {
                            binding.tvTop.text = res.toString()
                        }
                    }
                } catch (e: ArithmeticException) {
                    Toast.makeText(this, "Nolga bo'lish mumkin emas", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.polarity -> {
                if(binding.tvBottom.text!!.isEmpty()){
                    binding.tvBottom.text = ""
                }else if (!(binding.tvBottom.text.toString().contains("+") ||
                            binding.tvBottom.text.toString().contains("-") ||
                            binding.tvBottom.text.toString().contains("/") ||
                            binding.tvBottom.text.toString().contains("*") ||
                            binding.tvBottom.text.toString().contains("%"))){
                    binding.tvBottom.text = "-${binding.tvBottom.text}"
                }else if (binding.tvBottom.text.toString().substring(0,1) == "-" &&
                    !(binding.tvBottom.text.toString().substring(1,binding.tvBottom.text.toString().length).contains("+") ||
                            binding.tvBottom.text.toString().substring(1,binding.tvBottom.text.toString().length).contains("-")||
                            binding.tvBottom.text.toString().substring(1,binding.tvBottom.text.toString().length).contains("/") ||
                            binding.tvBottom.text.toString().substring(1,binding.tvBottom.text.toString().length).contains("*") ||
                            binding.tvBottom.text.toString().substring(1,binding.tvBottom.text.toString().length).contains("%"))){
                    binding.tvBottom.text = binding.tvBottom.text.toString().substring(1,binding.tvBottom.text.toString().length)
                }
            }
            R.id.point -> {
                if(binding.tvBottom.text.toString().isEmpty()){
                    binding.tvBottom.text = "0."
                }else if (binding.tvBottom.text.toString().substring(binding.tvBottom.text.toString().length-1).contains(operation.toString())){
                    binding.tvBottom.text = binding.tvBottom.text.toString()+"0."
                }else if (!binding.tvBottom.text.toString().substringAfterLast(operation.toString()).contains(".")){
                    binding.tvBottom.text = binding.tvBottom.text.toString()+"."
                }
            }
        }
    }

    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

}