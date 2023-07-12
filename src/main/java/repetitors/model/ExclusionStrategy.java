package repetitors.model;

import com.google.gson.FieldAttributes;
import repetitors.util.Exclude;
import repetitors.util.ExcludeT1;
import repetitors.util.ExcludeT2;
import repetitors.util.ExcludeT3;

public class ExclusionStrategy implements com.google.gson.ExclusionStrategy {
    private Exclude exclude;

    public ExclusionStrategy(Exclude exclude){
        this.exclude = exclude;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes field) {
        /*Class aClass = exclude == Exclude.EXCLUDET1 ? ExcludeT1.class : ExcludeT2.class;*/
        Class aClass;
        if(exclude == Exclude.EXCLUDET1){
            aClass = ExcludeT1.class;
        }
        else if(exclude == Exclude.EXCLUDET2){
            aClass = ExcludeT2.class;
        }
        else{
            aClass = ExcludeT3.class;
        }
        return field.getAnnotation(aClass) != null;
    }
}
