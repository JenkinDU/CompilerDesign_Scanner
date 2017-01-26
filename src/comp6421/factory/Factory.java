package comp6421.factory;

import comp6421.EType;
import comp6421.Token;
import comp6421.entity.Alphanum;
import comp6421.entity.Identify;

public class Factory {  
    public static Token createToken(EType type) {  
        switch (type) {  
        case ID:
            return new Identify();  
        case ALPHANUM:  
            return new Alphanum();  
        default:  
            break;  
        }  
        return null;  
    }  
}  