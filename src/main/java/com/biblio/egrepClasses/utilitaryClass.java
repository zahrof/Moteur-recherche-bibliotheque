package com.biblio.egrepClasses;

import com.biblio.egrepClasses.RegEx;

import java.util.HashMap;

public class utilitaryClass {

    private static Position app(ShortBook b, Position cursor, MinimalizedAutomaton a) {
        int i = b.getCharacter(cursor.page, cursor.line, cursor.col);
        MinimalizedAutomaton son = a.sons.getOrDefault(i, null);
        if(son!=null){
            if(son.terminal) return cursor;
            return app(b, Position.move(b,cursor,1),son);
        }
        else
        if (a.terminal)
            return cursor;
        else
            return null;
    }

    private static boolean egrep(ShortBook b, MinimalizedAutomaton ms){
        Position cursor = new Position();
        Position end;
        while(cursor!=null){
            end = app(b, cursor, ms);
            if(end!=null)return true;

            cursor = Position.move (b,cursor,1);
        }
        return false;
    }

    public static MinimalizedAutomaton convertRegExToMinAutomaton(RegEx re){
        RegExTree ret = null;
        try {
            ret = re.parse();
        } catch (Exception e) {
            System.err.println("  >> ERROR: syntax error for" +
                    " regEx \""+re.regEx+"\".");
        }
        EAutomaton s = new EAutomaton(new HashMap<>(),false);
        // add final state with epsilon
        s.put(-1, new EAutomaton(new HashMap<>(), true));
        s.initialize(ret);
        EAutomaton ndfa = s.determine(s);
        return  MinimalizedAutomaton.minimize(ndfa);
    }

    public static boolean regExIsPresent(ShortBook b, MinimalizedAutomaton ms) {
        return egrep(b,ms);
    }
}
