package com.biblio.algorithms.egrep;

public class utilitaryClass {

    private static Position app(ShortBook b, Position cursor, MinimalizedAutomaton a) {
        if(cursor==null) return null;
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

    public static boolean regExIsPresent(ShortBook b, MinimalizedAutomaton ms){
        Position cursor = new Position();
        Position end;
        while(cursor!=null){
            end = app(b, cursor, ms);
            if(end!=null){
                return true;
            }

            cursor = Position.move (b,cursor,1);
        }
        return false;
    }


}
