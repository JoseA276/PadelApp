package com.example.padelapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.padelapp.Player.Player;
import com.ventura.bracketslib.BracketsView;
import com.ventura.bracketslib.model.ColomnData;
import com.ventura.bracketslib.model.CompetitorData;
import com.ventura.bracketslib.model.MatchData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BracketTorneosActivity extends AppCompatActivity {
    BracketsView bracketsView;
    Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bracket_torneos);

        bracketsView = findViewById(R.id.bracket_view);


        // Definir los competidores para cuartos de final
        CompetitorData Jose = new CompetitorData("Jose", "2");
        CompetitorData Frank = new CompetitorData("Frank", "1");
        CompetitorData Yassin = new CompetitorData("Yassin", "1");
        CompetitorData Said = new CompetitorData("Said", "2");
        CompetitorData Ivan = new CompetitorData("Ivan", "2");
        CompetitorData Sara = new CompetitorData("Sara", "1");
        CompetitorData Raul = new CompetitorData("Raul", "2");
        CompetitorData Dani = new CompetitorData("Dani", "1");
        // Definir los partidos de cuartos de final
        MatchData match1QuarterFinal = new MatchData(Jose, Frank);
        MatchData match2QuarterFinal = new MatchData(Yassin, Said);
        MatchData match3QuarterFinal = new MatchData(Sara, Ivan);
        MatchData match4QuarterFinal = new MatchData(Raul, Dani);

        // Definir los partidos de semifinal
        MatchData match1SemiFinal = new MatchData(new CompetitorData("Jose", "2"), new CompetitorData("Said", "1"));
        MatchData match2SemiFinal = new MatchData(new CompetitorData("Ivan", "2"), new CompetitorData("Raul", "1"));

        // Definir el partido de final
        MatchData finalMatch = new MatchData(new CompetitorData("Ivan", "1"), new CompetitorData("Jose", "2"));

        // Configurar los brackets
        ColomnData quarterFinalColomn = new ColomnData(Arrays.asList(match1QuarterFinal, match2QuarterFinal, match3QuarterFinal, match4QuarterFinal));
        ColomnData semiFinalColomn = new ColomnData(Arrays.asList(match1SemiFinal, match2SemiFinal));
        ColomnData finalColomn = new ColomnData(Arrays.asList(finalMatch));

        bracketsView.setBracketsData(Arrays.asList(quarterFinalColomn, semiFinalColomn, finalColomn));
    }
}
