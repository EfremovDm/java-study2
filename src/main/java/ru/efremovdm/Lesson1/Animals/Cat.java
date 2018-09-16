package ru.efremovdm.Lesson1.Animals;

/**
 * Кошка
 */
public class Cat extends Animal implements Jumpable, Swimable {

    private final int RUN_LIMIT = 100;
    private final int SWIM_LIMIT = 100;
    private final float JUMP_LIMIT = 3.8f;
    private int swim_limit;
    private float jump_limit;

    public Cat(String name) {
        this.name = name;
        this.run_limit = RUN_LIMIT;
        swim_limit = SWIM_LIMIT;
        jump_limit = JUMP_LIMIT;
    }

    public Cat(String name, int run_limit, int swim_limit, float jump_limit){
        this.name = name;
        this.run_limit = run_limit;
        this.swim_limit = swim_limit;
        this.jump_limit = jump_limit;
    }

    @Override
    public String voice() {
        return "meow";
    }

    @Override
    public boolean swim(int length) {
        return swim_limit >= length;
    }

    @Override
    public boolean jump(float height) {
        return jump_limit >= height;
    }

    @Override
    public int getSwimLimit(){
        return swim_limit;
    }

    @Override
    public float getJumpLimit(){
        return jump_limit;
    }
}