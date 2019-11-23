package com.shinhan.dos.bonus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    TextView question_tyoe_txt;
    TextView explain_field;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        question_tyoe_txt = (TextView)findViewById(R.id.question_type_txt);
        explain_field = (TextView)findViewById(R.id.explain_field);

        String stockEx = "공제 대상 주식이란?\n자하면 세금 할인받는 스타트업 기준\n" +
                "\n" +
                "\n" +
                "\n" +
                "소득공제 혜택 대상기업은\n" +
                "\n" +
                "(1) 벤처기업 또는\n" +
                "\n" +
                "(2) 창업 7년 이내의 기술성 우수기업에\n" +
                "\n" +
                "해당할 경우,\n" +
                "\n" +
                "\n" +
                "\n" +
                "엔젤투자 소득공제 요건에 따라\n" +
                "\n" +
                "소득공제를 받을 수 있습니다.\nSTEP 1. \n" +
                "\n" +
                "소득공제 가능한 스타트업 투자하기\n" +
                "\n" +
                "\n" +
                "\n" +
                "이런 세금 혜택을 받으려면\n" +
                "\n" +
                "우선 소득공제가 가능한\n" +
                "\n" +
                "스타트업에 투자해야 합니다.\n" +
                "\n" +
                "\n" +
                "\n" +
                "앞서 언급한 것처럼\n" +
                "\n" +
                "벤처기업 또는 창업 7년 이내의\n" +
                "\n" +
                "기술성 우수기업인지를\n" +
                "\n" +
                "확인하고 투자를 진행하시기 바랍니다.\n" +
                "\n" +
                "\n" +
                "\n" +
                "STEP 2. \n" +
                "\n" +
                "투자 확인서 받기\n" +
                "\n" +
                "다음으로 투자한 기업에\n" +
                "\n" +
                "확인서를 요청해야 합니다.\n STEP 3. \n" +
                "\n" +
                "홈택스에 입력하기\n" +
                "\n" +
                "\n" +
                "\n" +
                "이제 발급받은 투자확인서와\n" +
                "\n" +
                "아래 사항입력을 통해\n" +
                "\n" +
                "소득공제 절차를 진행하면 됩니다.\n" +
                "\n" +
                "\n" +
                "\n" +
                "국세청 소득공제 신청 화면에서\n" +
                "\n" +
                "사업자등록번호, 금융기관명, \n" +
                "\n" +
                "계좌번호 등을 입력하고,\n" +
                "\n" +
                "\n" +
                "\n" +
                "회사에 증빙자료로\n" +
                "\n" +
                "투자확인서를 제출합니다.";
        String insuranceEx = "\"계약 만기 때 지급되는 급부금의 합계액이 이미 납입한 보험료를 초과하지 않는 보험\"을 보장성 보험이라고 합니다.\n" +
                "\n" +
                " \n" +
                "\n" +
                "만기가 되었을 때 환급금이 아예 없거나 있다고 하더라도 만기 까지 납입한 보험료 총합계 보다 만기 환급금이 적은 보험이 보장성 보험입니다.\n" +
                "\n" +
                " \n" +
                "\n" +
                "보장성보험의 반대 되는 말이 저축성 보험인데요, '저축'이라는 말이 들어간 것에서 알 수 있듯이 저축성 보험은 납입한 총 보험료 보다 돌려 받게 되는 돈이 더 많은 보험입니다.\n" +
                "\n" +
                " \n" +
                "\n" +
                "보장성 보험의 반대는 저축성 보험인데요, 해약 환급금이나 만기 환급금이 그때까지 납입한 보험료 보다 많은 보험이라고 보면 되겠습니다."+"\n"
                +"보장성 보험의 종류\n" +
                " \n" +
                "\n" +
                "다음과 같은 보험들이 보장성 보험들입니다.\n" +
                "\n" +
                " \n" +
                "\n" +
                "의료실비보험\n" +

                "암보험\n" +

                "건강보험\n" +
                "운전자 보험\n" +
                "치매 간병비 보험\n" +
                "어린이 보험\n" +
                "태아 보험\n" +
                "상해보험\n" +
                "정기보험\n" +
                "종신보험\n" +
                "화재보험\n" +
                "자동차보험";



        intent = getIntent();
        String name = intent.getExtras().getString("to"); /*String형*/


        if(name.equals("주식")){
            question_tyoe_txt.setText("주식으로 공제받기");
            explain_field.setText(stockEx);

        }else if(name.equals("보험")){
            question_tyoe_txt.setText("보험으로 공제받기");
            explain_field.setText(insuranceEx);
        }


    }
}
