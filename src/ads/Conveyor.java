package ads;

/**
 * Created by USER on 28.05.15.
 */
public class Conveyor {
    String first;
    String second;
    String printer;
    Integer time = 0;
    String result = "000000000";
    int capacity =4;
    public Conveyor(int capacity){
    this.capacity = capacity;
    }

    public void setPair(String[] pair){
        if(pair.length == 2){
            first = pair[0];
            second = pair[1];
            if(capacity == 4){
                result = "000000000";
            }
            if(capacity == 6){
                result = "0000000000000";
            }
            if(capacity == 8){
                result = "00000000000000000";
            }

        }else{
            first = pair[0];
            second = pair[1];
            result = pair[2];
        }

    }

    public void firstStep(StepInfo step){
        String someFirst;
        if(second.charAt(second.length() - 1) == '1'){
            result = sum(first,result);
            someFirst =first;

        }else {
            someFirst = "00000";
            result = sum("00000",result);
        }
        step.setFields(first+"("+Integer.parseInt(first,2) +")" + " " +second+"("+ Integer.parseInt(second,2)+")",
                result,someFirst);
    }

    public void secondStep(StepInfo step){
        step.setEnterData(result);
        if(second.charAt(second.length() - 2) == '1'){
            result = sdvig(result);
            step.setSdvig(result);
            result = sum(first,result);
            step.setPlus(first);
        }else {
            result = sdvig(result);
            step.setSdvig(result);
            result = sum("00000",result);
            step.setPlus("00000");
        }
    }

    public void thirdStep(StepInfo step){
        step.setEnterData(result);
        if(second.charAt(second.length() - 3) == '1'){
            result = sdvig(result);
            step.setSdvig(result);
            result = sum(first,result);
            step.setPlus(first);
        }else {
            result = sdvig(result);
            step.setSdvig(result);
            result = sum("00000",result);
            step.setPlus("00000");
        }
    }

    public void fourStep(StepInfo step){
        step.setEnterData(result);
        if(second.charAt(second.length() - 4) == '1'){
            result = sdvig(result);
            step.setSdvig(result);
            result = sum(first,result);
            step.setPlus(first);
            }else {
            result = sdvig(result);
            step.setSdvig(result);
            result = sum("00000",result);
            step.setPlus("00000");

        }
        result = sdvig(result);
        step.setVisibleResult(true);
        step.setResult(result);

    }
    public String[] getResult(){
        String[] res = new String[3];
        res[0]=first;
        res[1] = second;
        res[2] = result;
        return res;
    }
    private String sdvig (String res){
        char[] mas = res.toCharArray();
        char tmp;
        char tmp2;
        for(int i = mas.length-1; i >=1;i--){
            mas[i] = mas[i-1];
        }
        mas[0]='0';

        return String.valueOf(mas);
    }
    private String sum(String one, String two){
        String res;
        char[] mas = two.toCharArray();
        char memory = '0';
        for(int i = capacity; i >= 0; i--){
            if(one.charAt(i)=='1'&& mas[i] == '1'){
                if(memory == '0'){
                    memory = '1';
                    mas[i] = '0';
                }else{
                    mas[i] = '1';
                }
            }else{
                if(one.charAt(i)=='0'&& mas[i] == '0'){
                    if(memory == '1'){
                       mas[i]= '1';
                        memory ='0';
                    }else{
                        mas[i] = '0';
                    }
                }else {
                    if(one.charAt(i)=='0'^ mas[i] == '0'){
                        if(memory == '1'){
                            mas[i]= '0';
                        }else{
                            mas[i] = '1';
                        }
                    }
                }
            }

    }
        return String.valueOf(mas);
    }
}
