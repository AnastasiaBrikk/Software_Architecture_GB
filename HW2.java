// Пример реализации паттерна Строитель на языке Java:

public class HW2 {
    private String foundation;
    private String walls;
    private String roof;

    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    public void setWalls(String walls) {
        this.walls = walls;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public void show() {
        System.out.println("Foundation: " + foundation);
        System.out.println("Walls: " + walls);
        System.out.println("Roof: " + roof);
    }
}

public interface HouseBuilder {
    void buildFoundation();
    void buildWalls();
    void buildRoof();
    HW2 getHouse();
}

public class ConcreteHouseBuilder implements HouseBuilder {
    private HW2 house;

    public ConcreteHouseBuilder() {
        house = new HW2();
    }

    public void buildFoundation() {
        house.setFoundation("Concrete");
    }

    public void buildWalls() {
        house.setWalls("Bricks");
    }

    public void buildRoof() {
        house.setRoof("Tiles");
    }

    public HW2 getHouse() {
        return house;
    }
}

public class Director {
    private HouseBuilder builder;

    public Director(HouseBuilder builder) {
        this.builder = builder;
    }

    public void constructHouse() {
        builder.buildFoundation();
        builder.buildWalls();
        builder.buildRoof();
    }
}

public class Main {
    public static void main(String[] args) {
        HouseBuilder builder = new ConcreteHouseBuilder();
        Director director = new Director(builder);
        director.constructHouse();
        HW2 house = builder.getHouse();
        house.show();
    }
}

// В этом примере мы создали класс House и интерфейс HouseBuilder, который определяет методы для создания объектов класса House. Мы также создали класс ConcreteHouseBuilder, который реализует интерфейс HouseBuilder и определяет конкретные шаги для создания объекта House. Когда мы вызываем метод constructHouse() у объекта Director, он вызывает методы buildFoundation(), buildWalls() и buildRoof() у объекта ConcreteHouseBuilder, чтобы создать объект House. Затем мы вызываем метод getHouse() у объекта ConcreteHouseBuilder, чтобы получить объект House, который мы можем использовать. Строитель может использоваться для создания сложных объектов с помощью последовательности шагов.


// Пример реализации паттерна Mediator на языке Java:

import java.util.ArrayList;
import java.util.List;

interface Mediator {
    void addColleague(Colleague colleague);
    void sendMessage(Colleague sender, String message);
}

class ConcreteMediator implements Mediator {
    private List<Colleague> colleagues = new ArrayList<>();

    public void addColleague(Colleague colleague) {
        colleagues.add(colleague);
    }

    public void sendMessage(Colleague sender, String message) {
        for (Colleague colleague : colleagues) {
            if (colleague != sender) {
                colleague.receiveMessage(message);
            }
        }
    }
}

abstract class Colleague {
    protected Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void sendMessage(String message);
    public abstract void receiveMessage(String message);
}

class ConcreteColleague1 extends Colleague {
    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    public void sendMessage(String message) {
        mediator.sendMessage(this, message);
    }

    public void receiveMessage(String message) {
        System.out.println("ConcreteColleague1 received message: " + message);
    }
}

class ConcreteColleague2 extends Colleague {
    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }

    public void sendMessage(String message) {
        mediator.sendMessage(this, message);
    }

    public void receiveMessage(String message) {
        System.out.println("ConcreteColleague2 received message: " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();
        Colleague colleague1 = new ConcreteColleague1(mediator);
        Colleague colleague2 = new ConcreteColleague2(mediator);
        mediator.addColleague(colleague1);
        mediator.addColleague(colleague2);
        colleague1.sendMessage("Hello, colleague2!");
        colleague2.sendMessage("Hi, colleague1!");
    }
}

// В этом примере мы создали интерфейс Mediator, который определяет методы для связи между объектами классов Colleague. Мы также создали класс ConcreteMediator, который реализует интерфейс Mediator и определяет конкретные способы связи между объектами классов Colleague. Классы ConcreteColleague1 и ConcreteColleague2 наследуются от абстрактного класса Colleague и реализуют методы sendMessage() и receiveMessage(), которые используют объект Mediator для связи между объектами классов Colleague.
// В методе main() мы создаем объекты ConcreteColleague1 и ConcreteColleague2, добавляем их в объект ConcreteMediator и вызываем методы sendMessage() у каждого из них, чтобы отправить сообщения друг другу. Mediator может использоваться для связи между объектами классов Colleague через объект Mediator.


// Пример реализации паттерна Template Method на языке Java:

abstract class Game {
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();

    // Template method
    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }
}

class Cricket extends Game {
    void initialize() {
        System.out.println("Cricket Game Initialized! Start playing.");
    }

    void startPlay() {
        System.out.println("Cricket Game Started! Enjoy the game.");
    }

    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }
}

class Football extends Game {
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    void startPlay() {
        System.out.println("Football Game Started! Enjoy the game.");
    }

    void endPlay() {
        System.out.println("Football Game Finished!");
    }
}

public class Main {
    public static void main(String[] args) {
        Game cricket = new Cricket();
        cricket.play();

        System.out.println();

        Game football = new Football();
        football.play();
    }
}


// Классы Cricket и Football наследуются от Game и реализуют абстрактные методы initialize(), startPlay() и endPlay().
// Метод play() является шаблонным методом, который определяет последовательность выполнения операций. В данном случае, метод play() вызывает методы initialize(), startPlay() и endPlay() в определенном порядке. Конкретные классы переопределяют эти методы, чтобы предоставить свою специфическую реализацию.
// В методе main() мы создаем объекты Cricket и Football и вызываем метод play() для каждого из них, чтобы запустить игры. Template Method может использоваться для определения общей структуры алгоритма, позволяя подклассам предоставлять свою специфическую реализацию определенных шагов.


// Пример реализации паттерна Visitor.

interface Visitor {
    void visit(ElementA elementA);
    void visit(ElementB elementB);
}

abstract class Element {
    public abstract void accept(Visitor visitor);
}

class ElementA extends Element {
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operationA() {
        System.out.println("ElementA operationA");
    }
}

class ElementB extends Element {
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operationB() {
        System.out.println("ElementB operationB");
    }
}

class ConcreteVisitor implements Visitor {
    public void visit(ElementA elementA) {
        elementA.operationA();
    }

    public void visit(ElementB elementB) {
        elementB.operationB();
    }
}

public class Main {
    public static void main(String[] args) {
        Element[] elements = {new ElementA(), new ElementB()};
        Visitor visitor = new ConcreteVisitor();
        for (Element element : elements) {
            element.accept(visitor);
        }
    }
}

// // В этом примере мы создали интерфейс Visitor, который определяет методы visit() для каждого типа элемента, который может быть посещен. Мы также создали абстрактный класс Element, который определяет метод accept(), который принимает объект Visitor в качестве аргумента.
// Классы ElementA и ElementB наследуются от Element и реализуют метод accept(), который вызывает соответствующий метод visit() у объекта Visitor.
// Класс ConcreteVisitor реализует интерфейс Visitor и определяет конкретные действия, которые должны быть выполнены для каждого типа элемента.
// В методе main() мы создаем массив объектов Element и объект ConcreteVisitor, и затем вызываем метод accept() для каждого элемента, передавая ему объект ConcreteVisitor.



// Пример реализации паттерна Chain of Responsibility

abstract class Handler {
    private Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handleRequest(Request request) {
        if (canHandle(request)) {
            processRequest(request);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("No handler found for the request.");
        }
    }

    protected abstract boolean canHandle(Request request);
    protected abstract void processRequest(Request request);
}

class Request {
    private String type;
    private String content;

    public Request(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}

class ConcreteHandlerA extends Handler {
    protected boolean canHandle(Request request) {
        return request.getType().equals("TypeA");
    }

    protected void processRequest(Request request) {
        System.out.println("ConcreteHandlerA is handling the request: " + request.getContent());
    }
}

class ConcreteHandlerB extends Handler {
    protected boolean canHandle(Request request) {
        return request.getType().equals("TypeB");
    }

    protected void processRequest(Request request) {
        System.out.println("ConcreteHandlerB is handling the request: " + request.getContent());
    }
}

class ConcreteHandlerC extends Handler {
    protected boolean canHandle(Request request) {
        return request.getType().equals("TypeC");
    }

    protected void processRequest(Request request) {
        System.out.println("ConcreteHandlerC is handling the request: " + request.getContent());
    }
}

public class Main {
    public static void main(String[] args) {
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();
        Handler handlerC = new ConcreteHandlerC();

        handlerA.setNextHandler(handlerB);
        handlerB.setNextHandler(handlerC);

        Request request1 = new Request("TypeA", "Request 1");
        handlerA.handleRequest(request1);

        Request request2 = new Request("TypeB", "Request 2");
        handlerA.handleRequest(request2);

        Request request3 = new Request("TypeC", "Request 3");
        handlerA.handleRequest(request3);

        Request request4 = new Request("TypeD", "Request 4");
        handlerA.handleRequest(request4);
    }
}


// В этом примере мы создали абстрактный класс Handler, который определяет методы canHandle() и processRequest(). Классы ConcreteHandlerA, ConcreteHandlerB и ConcreteHandlerC наследуются от Handler и реализуют эти методы в соответствии с конкретными условиями обработки запросов.
// Класс Request представляет запрос, который будет обрабатываться цепочкой обработчиков.

// В методе main() мы создаем объекты обработчиков handlerA, handlerB и handlerC и устанавливаем связи между ними с помощью метода setNextHandler(). Затем мы создаем несколько объектов Request и вызываем метод handleRequest() у handlerA, чтобы передать запрос по цепочке обработчиков.

//  Chain of Responsibility может использоваться для создания цепочки обработчиков, где каждый обработчик решает, может ли он обработать запрос или передать его следующему обработчику в цепочке.