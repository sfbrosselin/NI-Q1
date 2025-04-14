package br.com.example.questaodois;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText inputSalario;
    private RadioGroup radioGroup;
    private TextView txtSalario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setSalario();

    }

    public void setSalario(){
        radioGroup = findViewById(R.id.radioGroup);
        txtSalario = findViewById(R.id.txtsalario);
        inputSalario = findViewById(R.id.inputSalario);

        radioGroup.setOnCheckedChangeListener((group, checkedId) ->{
            String salarioStr = inputSalario.getText().toString().trim();
            double salarioAtual = Double.parseDouble(salarioStr);

            if(salarioStr.isEmpty()){
                txtSalario.setText("Insira um valor válido.");
                return;
            }

            double novoSalario = 0d;

            if (checkedId == R.id.radioBtn40) {
                novoSalario = calculaSalario(salarioAtual, 40);
            } else if (checkedId == R.id.radioBtn45) {
                novoSalario = calculaSalario(salarioAtual, 45);
            } else if (checkedId == R.id.radioBtn50) {
                novoSalario = calculaSalario(salarioAtual, 50);
            }

            txtSalario.setText("Novo salário: R$ "+ String.format("%.2f", novoSalario));
        });


    }

    private static double calculaSalario(double salario, int porcentagem){
        double resultadoFinal = salario + ((porcentagem/100.0) * salario);

        return resultadoFinal;
    }
}