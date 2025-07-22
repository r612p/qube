import java.util.ArrayList;
public class AI{

public class GCD{
public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }           //used Ai for this we can modify it later I just need it temp for shors algo

    public static void main(String[] args) {
        int num1 = 48;
        int num2 = 18;

        int result = gcd(num1, num2);
        System.out.println("GCD of " + num1 + " and " + num2 + " is " + result);
    }
}





public static void main(String[] args){
        Qubit q1 = new Qubit(1,0,0,0);
        q1.HGate();
        System.out.print(q1);
       
}
}

class Qubit{
     private complexNumber a;
    private complexNumber b;
   

    public Qubit(){  //base constructor, represented by a∣0⟩+b∣1⟩, a=0, b=1

        a = new complexNumber(1, 0);                                                                                                               // by the way I changed it from 00 10 to 10 00 so it outputs 0
        b = new complexNumber(0, 0);

    }
        public Qubit(int ar, int ai, int br, int bi){  //constructor, only for specific examples where you want to create your own qubit. This is not possible irl.

        a = new complexNumber(ar, ai);
        b = new complexNumber(br, bi);

    }
    
    double[][] zeroREP = {
    {1},                        //how much of it is zero, ALSO THE BASE STATE
    {0}};                       //how much of it is one

    double[][] oneREP  = {
    {0},
    {1}};
       

    complexNumber[][] abt = {
    {a},
    {b}};

public double getProbabilityA(){
        return Math.pow(a.getReal(), 2) + Math.pow(a.getImaginary(), 2);
}

public double getProbabilityB(){
        return Math.pow(b.getReal(), 2) + Math.pow(b.getImaginary(), 2);
}

public complexNumber getA(){
        return a;
}

public complexNumber getB(){
        return b;
}

public String execute(){ //true is land on 1, false is land on 0
        double num = Math.random();
        if(num < Math.pow(a.getReal(), 2) + Math.pow(a.getImaginary(), 2))
                return "0";
        else
                return "1";
}

public void XGate(){
        complexNumber temp = a;
        a = b;
        b = temp;
}

public void YGate(){                                                                            //there may be an issue since b real yields -0.0 when it should be 0.0
        complexNumber newA = new complexNumber(b.getImaginary(), -b.getReal());

    
        complexNumber newB = new complexNumber(-a.getImaginary(), a.getReal());

        a = newA;
        b = newB;
}

public void ZGate(){
        b.setReal(-b.getReal());
        b.setImaginary(-b.getImaginary());
}

public void HGate(){
        double rttwo=1.0/Math.sqrt(2);
        complexNumber newA = new complexNumber((a.getReal()+b.getReal())*rttwo, (a.getImaginary()+b.getImaginary())*rttwo);
        complexNumber newB = new complexNumber((a.getReal()-b.getReal())*rttwo, (a.getImaginary()-b.getImaginary())*rttwo);

        a = newA;
        b = newB;
}

public void SGate(){
        complexNumber newB = new complexNumber(b.getImaginary()*-1,b.getReal());
        
        b = newB;

}

/*public void TGate(){
        double r = 1.0 / Math.sqrt(2);
        
        double newReal = b.getReal() * r - b.getImaginary() * r;
        double newImag = b.getReal() * r + b.getImaginary() * r;
        b = new complexNumber(newReal, newImag);

}*/

public void TGate(){
        double r = 1.0 / Math.sqrt(2);
        complexNumber multiplier = new complexNumber(r,r);

        b.complexMultiply(multiplier);
        

}
public void SDaggerGate(){
       complexNumber newB = new complexNumber(b.getImaginary()*-1,b.getReal());
        
        b = newB; 

}

public void TDaggerGate(){
        double r = 1.0 / Math.sqrt(2);
        
        complexNumber multiplier = new complexNumber(r,-r);

        b.complexMultiply(multiplier);

}

public String toString(){
        String realA = "" + getA().getReal();
        String iA = "" + getA().getImaginary();
        String realB = "" + getB().getReal();
        String iB = "" + getB().getImaginary();


        return "(" + realA + " + " + iA + "i)" + "|0>" + " + (" + realB + " + " + iB + "i)" + "|0>";
}
}



class MultiQubit{

public ArrayList<Qubit> entangled = new ArrayList<>();
public ArrayList<String> entangledOutputs = new ArrayList<>();
private int numQubits;


public String[][] workspace;


public MultiQubit(int num){
        numQubits = num;
        for(int i = 0; i<num; i++){
                entangled.add(new Qubit());
        }


        for(int i = 0; i<(int)Math.pow(2, entangled.size()); i++)
        {
                entangledOutputs.add(Integer.toBinaryString(i));
        }
                workspace = new String[numQubits][10];//<-- 10 can be changed later, idk if we want it to be dynamic or not
        



}

public String executeMulti(){
        String answer = "";
        for(int i = 0; i<entangled.size(); i++){
                answer = answer + entangled.get(i).execute()+" ";
        }
        return answer;  //return entangledOutputs.get((int)(Math.random() * entangledOutputs.size())); //random for now, will have to make a generator based on each entangled output's chance

}


        public String executeCircut(){
                for(int c = 0; c < workspace[numQubits].length; c++){                           //something wrong here
                        for(int r = 0; r < workspace.length; r++){
                                if (workspace[c][r].equals("X")) {
                                    getQubitFromMulti(c).XGate();
                                }

                                if (workspace[c][r].equals("Y")) {
                                    getQubitFromMulti(c).YGate();
                                }

                                if (workspace[c][r].equals("Z")) {
                                    getQubitFromMulti(c).ZGate();
                                }

                                if (workspace[c][r].equals("S")) {
                                    getQubitFromMulti(c).SGate();
                                }

                                if (workspace[c][r].equals("T")) {
                                    getQubitFromMulti(c).TGate();
                                }

                                if (workspace[c][r].equals("SDagger")) {
                                    getQubitFromMulti(c).SDaggerGate();
                                }

                                if (workspace[c][r].equals("TDagger")) {
                                    getQubitFromMulti(c).TDaggerGate();
                                }

                                if (workspace[c][r].equals("Hadamard")) {       //theres gotta be a way to make this shorter
                                    getQubitFromMulti(c).HGate();
                                }       
                        }
                }
                                        return executeMulti();                  // have not tested, idk if this will work or nah
        }


public void addGate(String gate, int qube, int layer){
              workspace[qube][layer] = (gate);
}

public int getNumQubits(){
        return numQubits;
}

public Qubit getQubitFromMulti(int selection){
        return entangled.get(selection);
}

}








//complex number class start------------------------------------------------------------------------------------------------------
 class complexNumber{
        public double real;
        public double imaginary;

        public complexNumber(double real, double imaginary){
                this.real = real;
                this.imaginary = imaginary;
        }


           public complexNumber(){
                real = 0;
                imaginary = 0;
        }

        public void complexAdd(double num){ //adds a real number to the complex number
                real += num;
        }

        public void complexAdd(complexNumber num){ //adds complex to complex
                real += num.getReal();
                imaginary += num.getImaginary();
        }

        public void complexMultiply(complexNumber num){
                double a = real;
                double b = imaginary;
                double c = num.getReal();
                double d = num.getImaginary();

                real = a * c - b * d;
                imaginary = a * d + b * c;
        }

//getters
        public double getReal(){
                return real;
        }

        public double getImaginary(){
                return imaginary;
        }

//setters
        public void setReal(double num){
                real = num;
        }

        public void setImaginary(double num){
                imaginary = num;
        }
}       
