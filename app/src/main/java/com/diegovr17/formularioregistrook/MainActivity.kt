package com.diegovr17.formularioregistrook


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var etNombre: EditText
    lateinit var etEmail: EditText
    lateinit var etTelefono: EditText
    lateinit var etPassword: EditText
    lateinit var etRepPassword: EditText
    lateinit var tvDatos: TextView
    lateinit var Button: Button

    lateinit var SexoGrupo: RadioGroup
    lateinit var ButtonHombre: RadioButton
    lateinit var ButtonMujer: RadioButton


    lateinit var Hobby1: CheckBox
    lateinit var Hobby2: CheckBox
    lateinit var Hobby3: CheckBox
    lateinit var Hobby4: CheckBox
    lateinit var SpinnerC: Spinner



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvFecha.setOnClickListener(this)

        etNombre = findViewById(R.id.etNom) as EditText
        etEmail  = findViewById(R.id.etEmail) as EditText
        etTelefono = findViewById(R.id.etTel) as EditText
        etPassword = findViewById(R.id.etPass) as EditText
        etRepPassword = findViewById(R.id.etPass1) as EditText

        tvDatos = findViewById(R.id.tvDatos) as TextView
        Button = findViewById(R.id.Boton) as Button

        SexoGrupo = findViewById(R.id.radioG) as RadioGroup
        ButtonHombre = findViewById(R.id.Hombre) as RadioButton
        ButtonMujer = findViewById(R.id.Mujer) as RadioButton

        Hobby1 = findViewById(R.id.Hobby1) as CheckBox
        Hobby2 = findViewById(R.id.Hobby2) as CheckBox
        Hobby3 = findViewById(R.id.Hobby3) as CheckBox
        Hobby4 = findViewById(R.id.Hobby4) as CheckBox

        SpinnerC = findViewById(R.id.spiLN) as Spinner

        var CiudadN = ""
        var Ciudades = arrayOf("Medellín","Bogotá","Calí","Barranquilla","Cartagena")
        SpinnerC.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Ciudades)
        SpinnerC.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                CiudadN = "Medellín"
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                CiudadN = Ciudades[position]
            }

        }

        var ban: Int


        Button.setOnClickListener(View.OnClickListener {

            val Nombre: String = etNombre.text.toString()
            if(Nombre == ""){
                ban = 1
            }
            else{
                ban = 0
            }

            val Email: String  = etEmail.text.toString()
            if(Email == ""){
                ban = 1
            }
            else{
                ban = 0
            }

            val Telefono: String = etTelefono.text.toString()
            if(Telefono == ""){
                ban = 1
            }
            else{
                ban = 0
            }

            val Password: String = etPassword.text.toString()
            if(Password == ""){
                ban = 1
            }
            else{
                ban = 0
            }

            val RepPassword: String = etRepPassword.text.toString()
            if(RepPassword == ""){
                ban = 1
            }
            else{
                ban = 0
            }

            val FechaN: String = tvFecha.text.toString()
            if(FechaN == "FechaNacimiento"){
                ban = 1
            }
            else{
                ban = 0
            }


            var Sexo = ""
            var Hobby = ""

            if(SexoGrupo.checkedRadioButtonId != -1){
                if(ButtonHombre.isChecked){
                    Sexo+="Hombre"

                }
                else if(ButtonMujer.isChecked){
                    Sexo+="Mujer"
                }
            }

            if(Hobby1.isChecked){
                Hobby+="Deporte"
            }
            else if(Hobby2.isChecked){
                Hobby+="Pasear"
            }
            else if(Hobby3.isChecked){
                Hobby+="Leer"
            }
            else if(Hobby4.isChecked){
                Hobby+="Dormir"
            }


            if(Password == RepPassword) {
                if (ban == 0 ){
                    tvDatos.text = " -> Su nombre es:" + Nombre + "\n" +
                            " -> Su Correo es:" + Email + "\n" +
                            " -> Su Teléfono es:" + Telefono + "\n" +
                            " -> Su Sexo es:" + Sexo + "\n" +
                            " -> Su Hobby es:" + Hobby + "\n" +
                            " -> Su FechaN es:" + FechaN + "\n" +
                            " -> Su CiudadN es:" + CiudadN
                }
                else {
                    tvDatos.text = " -> Campo Vacío"

                }

            }
            else{
                tvDatos.text = " -> Las contraseñas no coinciden"
            }
            CiudadN = ""
        }
        )

    }

    private fun openDatePicker(){
        val calendar: Calendar = Calendar.getInstance()
        val setDateSetListener: DatePickerDialog.OnDateSetListener =
            DatePickerDialog.OnDateSetListener{_,Año,Mes,Día ->
                calendar.set(Calendar.YEAR,Año)
                calendar.set(Calendar.MONTH,Mes)
                calendar.set(Calendar.DAY_OF_MONTH,Día)
                tvFecha.text = getReadableDate(calendar)
            }
        DatePickerDialog(this,setDateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    @SuppressLint("SimpleDateFormat")
    private fun getReadableDate(calendar: Calendar):String{
        val df = SimpleDateFormat("dd/MM/yyyy")
        return df.format(calendar.time)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tvFecha -> openDatePicker()
        }
    }
}
