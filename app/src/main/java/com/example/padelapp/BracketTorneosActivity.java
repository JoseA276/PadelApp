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
        CompetitorData brazil = new CompetitorData("Brazil", "1");
        CompetitorData england = new CompetitorData("England", "2");
        CompetitorData argentina = new CompetitorData("Argentina", "1");
        CompetitorData russia = new CompetitorData("Russia", "2");
        CompetitorData brazil2 = new CompetitorData("Brazil2", "2");
        CompetitorData england2 = new CompetitorData("England2", "1");
        CompetitorData argentina2 = new CompetitorData("Argentina2", "2");
        CompetitorData russia2 = new CompetitorData("Russia2", "1");
        // Definir los partidos de cuartos de final
        MatchData match1QuarterFinal = new MatchData(brazil, england);
        MatchData match2QuarterFinal = new MatchData(argentina, russia);
        MatchData match3QuarterFinal = new MatchData(brazil2, england2);
        MatchData match4QuarterFinal = new MatchData(argentina2, russia2);

        // Definir los partidos de semifinal
        MatchData match1SemiFinal = new MatchData(new CompetitorData("england", "0"), new CompetitorData("Brazil2", "0"));
        MatchData match2SemiFinal = new MatchData(new CompetitorData("Russia", "2"), new CompetitorData("Argentina2", "2"));

        // Definir el partido de final
        MatchData finalMatch = new MatchData(new CompetitorData("England", "1"), new CompetitorData("Argentina2", "2"));

        // Configurar los brackets
        ColomnData quarterFinalColomn = new ColomnData(Arrays.asList(match1QuarterFinal, match2QuarterFinal, match3QuarterFinal, match4QuarterFinal));
        ColomnData semiFinalColomn = new ColomnData(Arrays.asList(match1SemiFinal, match2SemiFinal));
        ColomnData finalColomn = new ColomnData(Arrays.asList(finalMatch));

        bracketsView.setBracketsData(Arrays.asList(quarterFinalColomn, semiFinalColomn, finalColomn));
    }
}
