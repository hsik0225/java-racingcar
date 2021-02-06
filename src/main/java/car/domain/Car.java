package car.domain;

import car.domain.engine.Engine;
import car.domain.engine.RacingEngine;

import java.util.Objects;

final class Car {
    
    private static final int DEFAULT_POSITION = 0;
    
    private final Name carName;
    
    private final Position position;
    
    private final Engine engine;
    
<<<<<<< HEAD
    private Car(Builder builder) {
        this.carName = builder.carName;
        this.position = builder.position;
        this.engine = builder.engine;
=======
    public Car(String carName) {
        this(carName, new RacingEngine());
    }
    
    public Car(String carName, Engine engine) {
        this(Name.from(carName), new Position(), engine);
    }
    
    public Car(String carName, int position, Engine engine) {
        this(Name.from(carName), Position.from(position), engine);
>>>>>>> 833c9e1... refactor(car):빌더 패턴 삭제 및 빌더 패턴을 사용하는 코드 수정
    }
    
    private Car(Name carName, Position position, Engine engine) {
        this.carName = carName;
        this.position = position;
        this.engine = engine;
    }
    
    public Car move() {
        if (engine.isEnoughFuel()) {
            return move(this);
        }
        
        return this;
    }
    
    private Car move(Car car) {
        return new Car(car.carName, car.position.increase(), car.engine);
    }
    
    public Score getScore() {
        return new Score(carName.getName(), position.getPosition());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(carName, car.carName) && Objects.equals(position, car.position);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(carName, position);
    }
    
    static class Builder {
        
        private final Name carName;
        
        private Position position = Position.from(DEFAULT_POSITION);
        
        private Engine engine = new RacingEngine();
        
        public Builder(String carName) {
            this.carName = Name.from(carName);
        }
        
        public Builder withPosition(int position) {
            this.position = Position.from(position);
            return this;
        }
        
        Builder withFakeEngine(int moveCondition) {
            this.engine = new Engine.Fake(moveCondition);
            return this;
        }
        
        public Car build() {
            return new Car(this);
        }
    }
}
