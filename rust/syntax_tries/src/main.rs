fn main() {
    //* Variable bindings
    let x = 5;
    //** with [pattern]
    let (x, y) = (1, 2);
    //** along with type specification
    let x: i32 = 5;
    //** default are immutable, mutable?
    let mut x = 5;
    x = 10;
    //** x can be defined multiple times, it 's called [shadowing]
    //** using an uninitialized variable will cause error
    println!("value of x is {}", x);
    //** blocks define scopes of variables
    {
        let y = 100;
        println!("x:{} y:{}", x, y);
    }
}

