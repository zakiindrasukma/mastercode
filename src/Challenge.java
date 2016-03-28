import java.util.*;

/**
 * Created by zakiindra on 3/28/16.
 */
public class Challenge {
    public static void main(String[] args) {
        //
        // 1. Change car engine
        //
        Car car = new Car();
        Engine v6Engine = new V6Engine();

        // PROBLEM
        double v6EngineSpeed = car.getMaxSpeed();
        System.out.println("1.1 : " + (v6EngineSpeed == v6Engine.getMaxSpeed()));

        Engine turbopropEngine = new TurbopropEngine();
        car.changeEngine(turbopropEngine);
        double turbopropEngineSpeed = car.getMaxSpeed();

        // PROBLEM
        System.out.println("1.2 : " + (turbopropEngineSpeed > v6EngineSpeed));


        //
        // 2. Custom HTML-like markup language
        //
        MarkupElement root = new RootElement("[customml]", "[/customml]");
        MarkupElement body = new BodyElement("[body]", "[/body]");
        MarkupElement italic = new ItalicElement("[i]", "[/i]");

        italic.setContent("I am italic.");

        // PROBLEM
        System.out.println("2.1 : " + italic.produceOutput()
                .equals("[i]I am italic.[/i]"));

        root.addChildren(body);
        body.addChildren(italic);

        System.out.println("2.2 : " + root.produceOutput()
                .equals("[customml][body][i]I am italic.[/i][/body][/customml]"));


        //
        // 3. Implement stack that will output n items at once
        //
        WeirdStack<Integer> weirdStack = new WeirdStack<Integer>(2); // will output 2 items at once.
        weirdStack.push(1);
        weirdStack.push(2);
        weirdStack.push(3);

        java.util.List<Integer> popped = weirdStack.pop();
        // PROBLEM: Verify that popped is [3, 2]
        System.out.println("3.1 : " + popped.equals(java.util.Arrays.asList(3, 2)));

        weirdStack.setPopSize(3); // will output 3 items at once.
        weirdStack.push(4);
        weirdStack.push(5);

        java.util.List<Integer> poppedAgain = weirdStack.pop();
        // PROBLEM: Verify that poppedAgain is [5, 4, 1]
        System.out.println("3.2 : " + poppedAgain.equals(java.util.Arrays.asList(5, 4, 1)));
    }
}

//
// PROBLEM 1. Change car engine.
//
class Car {
    Engine engine;

    public Car(){
        engine = new V6Engine();
    }

    public void changeEngine(Engine newEngine){
        engine = newEngine;
    }

    public double getMaxSpeed(){
        return engine.getMaxSpeed();
    }

}

interface Engine {
    public double getMaxSpeed();

}
class V6Engine implements Engine {
    public double maxSpeed = 230;
    public double getMaxSpeed(){
        return maxSpeed;
    }
}
class TurbopropEngine implements Engine {
    public double maxSpeed = 380;
    public double getMaxSpeed(){
        return maxSpeed;
    }
}

//
// PROBLEM 2. Custom HTML-like markup language.
//
class MarkupElement {
    String content = "";
    String openingTag, closingTag;
    MarkupElement childrenElement = null;

    public void setContent(String newContent){
        this.content = newContent;
    }

    public void addChildren(MarkupElement element){
        this.childrenElement = element;
    }

    public String produceOutput(){
        if(childrenElement!=null){
            return openingTag + childrenElement.produceOutput() + closingTag;
        } else
         return openingTag+content+closingTag;
    }
}
class RootElement extends MarkupElement {
    public RootElement(String openingTag, String closingTag){
        this.openingTag = openingTag;
        this.closingTag = closingTag;
    }
}
class BodyElement extends MarkupElement {
    public BodyElement(String openingTag, String closingTag){
        this.openingTag = openingTag;
        this.closingTag = closingTag;
    }
}
class ItalicElement extends MarkupElement {
    public ItalicElement(String openingTag, String closingTag){
        this.openingTag = openingTag;
        this.closingTag = closingTag;
    }
}

//
// PROBLEM 3. Implement stack that will output n items at once.
//
class WeirdStack<T> {
    int popSize;
    Stack<T> stack;

    public WeirdStack(int n){
        this.popSize = n;
        this.stack = new Stack<>();
    }

    public ArrayList pop(){
        if(stack.isEmpty()) return null;
        else {
            ArrayList<T> bucket = new ArrayList<>();
            if(stack.size() < popSize) {
                while (!stack.isEmpty()) {
                    bucket.add(stack.pop());
                }
            } else {
                for (int i=0; i<popSize; i++){
                    bucket.add(stack.pop());
                }
            }
            return bucket;
        }
    }

    public void push(T item){
        stack.add(item);
    }

    public void setPopSize(int n){
        this.popSize = n;
    }


}