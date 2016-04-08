use std::sync::Arc;
use std::cell::Cell;

fn main() {
    //val_demo();
    //a_function();
    //diverge();
}

// + Variable bindings
fn val_demo() {
    let x = 5;
    // + + with [pattern]
    let ( x, y ) = ( 1, 2 );
    // + + along with type specification
    let x: i32 = 5;
    // + + default are immutable, mutable?
    let mut x = 5;
    x = 10;
    // + + x can be defined multiple times, it 's called [shadowing]
    // + + using an uninitialized variable will cause error
    println!( "value of x is {}", x );
    // + + blocks define scopes of variables
    {
        let y = 100;
        println!( "x:{} y:{}", x, y );
    }
}

// + Function
fn a_function() {
}
// + + parameters must have declared types
fn print_sum( a : i32, b : i32 ) {
    println!( "The sum is {}", a + b );
}
// + + an ?expression return the value
fn add_one( a : i32 ) -> i32 {
    a + 1 // notice the lack of ;
}
// + + ?poor styled return
fn add_two( a : i32 ) -> i32 {
    return a + 2;
}
// + + !diverging function
fn diverge() -> ! {
    panic!( "This function never returns!" );
}
// + + + 'value' of diverging function can be assigned to any type
// + + + set environment variable RUST_BACKTRACE=1 to see details in Rust crashes
// + + function variables
fn fn_val_demo() {
    let f : fn( i32 ) -> i32 = add_one;
    let six = f( 5 );
}

// + primitive types
fn primitive_types() {
    // + + boolean
    let x = true;
    let y : bool = false;
    // + + char
    let x = 'x';
    // + + numeric
    // i8 i16 i32 i64
    // u8 u16 u32 u64
    // isize usize -- machine dependent
    // f32 f64
    let x : isize = 100;
    // + + array
    let a = [1, 2, 3]; // a: [i32; 3]
    let mut m = [1, 2, 3]; // m: [i32; 3]
    // + + + length
    println!( "a has length {}", a.len() );
    // + + + element access
    let names = ["Graydon", "Brian", "Niko"]; // names: [&str; 3]
    println!( "The second name is: {}", names[1] );
    // + + slice
    let a = [0, 1, 2, 3, 4];
    let complete = &a[..]; // A slice containing all of the elements in a
    let middle = &a[1..4]; // A slice of a: only the elements 1, 2, and 3
    // + + str?
    // + + tuple
    let x = (1, "hello");
    // + + + ?type
    let x: (i32, &str) = (1, "hello");
    // + + + ?assign
    let mut x = (1, 2); // x: (i32, i32)
    let y = (2, 3); // y: (i32, i32)
    x = y;
    // + + + ?de-structure
    let (x, y, z) = (1, 2, 3);
    println!("x is {}", x);
    // + + + tuple vs value in ()
    let x = (0,); // single-element tuple
    let x = (0); // zero in parentheses
    // + + + ?index
    let tuple = (1, 2, 3);
    let x = tuple.0;
    let y = tuple.1;
    let z = tuple.2;
    // + + function
    let f : fn( i32 ) -> i32 = add_one;
}

// + comments
// normal comments
/// doc comments with markdown syntax
// //! doc comments containing ?items, cause ?error when using in ?certain situation
// extract doc with rustdoc tool

// + if
fn if_demo() {
    let x = 5;
    if x == 5 {
        println!( "Thank god!" );
    }
    // + + ?else
    if x == 5 {
        println!( "Thank god!" );
    } else {
        println!( "No!" );
    }
    // + + ?else if
    if x == 5 {
        println!( "Thank god!" );
    } else if x == 6 {
        println!( "Thank flying spaghetti!" );
    } else {
        println!( "Thank Obama!" );
    }
    // + + ?expression
    let y = if x == 5 {
        10
    } else {
        15
    }; // y: i32
}

// + loop
fn loop_demo() {
    // + + !infinite loop
    loop {
        // + + till ?break
        break;
    }
    // + + while
    let mut x = 5; // mut x: i32
    let mut done = false; // mut done: boolean

    while !done {
        x += x - 3;

        println!("{}", x);

        if x % 5 == 0 {
            done = true;
        }
    }
    // + + for
    // + + + ?iterator
    for x in 0..10 {
        println!("{}", x); // x: i32
    }
    // + + + ?enumerate
    for (i,j) in (5..10).enumerate() {
        println!("i = {} and j = {}", i, j);
    }
    // + + break, continue
    let mut x = 5;

    loop {
        x += x - 3;

        println!("{}", x);

        if x % 5 == 0 { break; }
    }
    // + + loop labels
    'outer: for x in 0..10 {
        'inner: for y in 0..10 {
            if x % 2 == 0 { continue 'outer; } // continues the loop over x
            if y % 2 == 0 { continue 'inner; } // continues the loop over y
            println!("x: {}, y: {}", x, y);
        }
    }
}

// + ownership
// -> ?borrowing, -> ?lifetime
fn ownership_explain() {
    // variable bindings have ownership
    let v = vec![1, 2, 3];
    // when come into scope, heap space is allocated
    // the memory space will be destroyed after it is out of scope
    // Rust ensure there 's !exactly one binding at a time for each resource
    // the new binding will take ?ownership of the resource
    let v2 = v;
    // after this line, usage of v will cause 'use of moved value' error
    // the same ownership taking happens if a function is called
    take(v2);
    // v2 is taken, can not use it now

    // there 's a trait called Copy
    // types that implemented it will not be ownership taken
    let v = 1;
    let v2 = v;
    println!("v is: {}", v);
    // fortunately, all primitive types implement Copy trait
    // types that have external pointer will not implement it by default

    // putting ownership back is tedious, borrowing is used for this purpose
    let v1 = vec![1, 2, 3];
    let v2 = vec![1, 2, 3];

    let (v1, v2, answer) = foo(v1, v2);
}

fn take(v: Vec<i32>) {
    // the function detail is not importance
    // just passed the parameter, and it is taken
}

fn foo(v1: Vec<i32>, v2: Vec<i32>) -> (Vec<i32>, Vec<i32>, i32) {
    // do stuff with v1 and v2
    // hand back ownership, and the result of our function
    (v1, v2, 42)
}

// + borrowing
fn borrowing_explain() {
    // + + reference
    let v1 = vec![1, 2, 3];
    let v2 = vec![1, 2, 3];

    let answer = foo_with_ref(&v1, &v2);
    // we can use v1 and v2 here!

    // + + mut reference
    let mut x = 5;
    {
        let y = &mut x;
        *y += 1;
    }
    println!("{}", x);
    // ! ! ! notice the block scope
    // at one time, there may be either, but not both
    // - multiple immutable references
    // - exactly 1 mutable reference
}

fn foo_with_ref(v1: &Vec<i32>, v2: &Vec<i32>) -> i32 {
    // do stuff with v1 and v2
    // ! ! ! references are immutable
    // v1.push(5); // will cause error

    // return the answer
    42
}

// + lifetimes
fn lifetimes_explain() {
    // ?zero-cost abstraction
}

// + + implicit lifetime
fn foo_implicit_lifetime(x: &i32) {
}

// + + explicit lifetime
fn foo_explicit_lifetime<'a>(x: &'a i32, y: &'a mut i32) {
}

// + + in struct
struct Foo<'a> {
    x: &'a i32,
}

// + + in struct impl
// impl<'a> declare, Foo<'a> use
impl<'a> Foo<'a> {
    fn x(&self) -> &'a i32 { self.x }
}

fn Foo_struct_demo() {
    let y = &5; // this is the same as `let _y = 5; let y = &_y;`
    let f = Foo { x: y };

    println!("{}", f.x);
    println!("{}", f.x());
}

// + + multiple lifetimes
fn foo_multi_lifetime<'a, 'b>(x: &'a str, y: &'b str) -> &'a str {
    "test"
}

// + mutability
fn mut_explain() {
    // + + immutable by default
    let mut x = 5;
    x = 6; // no problem!
    // + + mutable reference
    let y = &mut x;
    *y = 5;
    // + + mutable mutable reference
    let mut z = 3;
    let mut t = &mut z;
    // part of a ?pattern
    let (mut a, b) = (5, 6);
    // + + ?interior vs ?exterior mutability
    let x1 = Arc::new(5);
    let y1 = x1.clone();
    // + + field-level mutability
    let mut a = Point { x: 5, y: 6 };
    a.x = 10;
    let b = Point { x: 5, y: 6};

    let point = Mut_Point { x: 5, y: Cell::new(6) };
    point.y.set(7);
    println!("y: {:?}", point.y);
}

// + + Structures can not have mutable fields
struct Point {
    x: i32,
    y: i32,
}

// + + Field level mutability simulation with Cell
struct Mut_Point {
    x: i32,
    y: Cell<i32>,
}

// + structure
fn struct_explain() {
    let origin = Point { x:0, y:0 };
    println!("The origin is at ({}, {})", origin.x, origin.y);
    // + + mutable
    let mut a = Point { x:0, y:0 };
    a.x = 5;
    // + + with references
    let mut point = Point { x: 0, y: 0 };

    {
        let r = PointRef { x: &mut point.x, y: &mut point.y };

        *r.x = 5;
        *r.y = 6;
    }

    assert_eq!(5, point.x);
    assert_eq!(6, point.y);
    // + + update syntax
    let origin = Point3d { x: 0, y: 0, z: 0 };
    let point = Point3d { z: 1, x: 2, .. origin };
    // + + tuple structure
    let black = Color(0, 0, 0);
    // + + new type pattern
    struct Inches(i32);

    let length = Inches(10);

    let Inches(integer_length) = length;
    println!("length is {} inches", integer_length);
    // + + unit structure
    let x = Electron;
}

struct PointRef<'a> {
    x: &'a mut i32,
    y: &'a mut i32,
}

struct Point3d {
    x: i32,
    y: i32,
    z: i32,
}

struct Color(i32, i32, i32);

struct Electron;

// + enumerate
enum Message {
    Quit,
    ChangeColor(i32, i32, i32),
    Move { x: i32, y: i32 },
    Write(String),
}

fn enum_demo() {
    let x: Message = Message::Move { x: 3, y: 4 };
    // + + enumerate can be unstructured with ?match
    // + + enumerate constructor as ?function
    let v = vec!["Hello".to_string(), "World".to_string()];
    let v1: Vec<Message> = v.into_iter().map(Message::Write).collect();
}

// + match
fn match_demo() {
    // + + ?normal usage
    let x = 5;
    match x {
        1 => println!("one"),
        2 => println!("two"),
        3 => println!("three"),
        4 => println!("four"),
        5 => println!("five"),
        _ => println!("something else"),
    }
    // + + as expression
    let number = match x {
        1 => "one",
        2 => "two",
        3 => "three",
        4 => "four",
        5 => "five",
        _ => "something else",
    };
    // + + with enumerate
    let msg : Message = Message::Quit; // ?how assign Write with String
    match msg {
        Message::Quit => quit(),
        Message::ChangeColor(r, g, b) => change_color(r, g, b),
        Message::Move { x: x, y: y } => move_cursor(x, y),
        Message::Write(s) => println!("{}", s),
    };
}

fn quit() { /* ... */ }
fn change_color(r: i32, g: i32, b: i32) { /* ... */ }
fn move_cursor(x: i32, y: i32) { /* ... */ }

// + pattern
fn pattern_explain() {
    // + + literal
    let x = 1;

    match x {
        1 => println!("one"),
        2 => println!("two"),
        3 => println!("three"),
        _ => println!("anything"),
    }
    // + + shadowing
    let c = 'c';

    match c {
        x => println!("x: {} c: {}", x, c),
    }

    println!("x: {}", x);
    // + + multiple patterns
    match x {
        1 | 2 => println!("one or two"),
        3 => println!("three"),
        _ => println!("anything"),
    }
    // + + de-structuring
    let origin = Point { x: 0, y: 0 };

    match origin {
        Point { x, y } => println!("({},{})", x, y),
    }
    // + + + giving de-structured value a name
    match origin {
        Point { x: x1, y: y1 } => println!("({},{})", x1, y1),
    }
    // + + + omit some values
    match origin {
        Point { y, .. } => println!("y is {}", y),
    }
    // + + omit one or some
    let (x, _, z) = coordinate();
    let x = OptionalTuple::Value(5, -2, 3);
    match x {
        OptionalTuple::Value(..) => println!("Got a tuple!"),
        OptionalTuple::Missing => println!("No such luck."),
    }
    // + + ?reference
    let x = 5;
    match x {
        ref r => println!("Got a reference to {}", r),
    }

    let mut x = 5;
    match x {
        ref mut mr => println!("Got a mutable reference to {}", mr),
    }
    // + + range
    let x = 1;
    match x {
        1 ... 5 => println!("one through five"),
        _ => println!("anything"),
    }

    let x = 'z';
    match x {
        'a' ... 'j' => println!("early letter"),
        'k' ... 'z' => println!("late letter"),
        _ => println!("something else"),
    }
    // + + binding
    let x = 1;
    match x {
        e @ 1 ... 5 => println!("got a range element {}", e),
        _ => println!("anything"),
    }
    // + + binding with multiple patterns
    match x {
        e @ 1 ... 5 | e @ 8 ... 10 => println!("got a range element {}", e),
        _ => println!("anything"),
    }
    // + + match guards
    let x = OptionalTuple::Value(5,1,2);
    match x {
        OptionalTuple::Value(i, _, _) if i > 5 => println!("Got an int bigger than five!"),
        OptionalTuple::Value(..) => println!("Got an integer!"),
        OptionalTuple::Missing => println!("No such luck."),
    }
    // 'if' has lower priority than '|'
}

fn coordinate() -> (i32, i32, i32) {
    // generate and return some sort of triple tuple
    (1, 2, 3)
}

enum OptionalTuple {
    Value(i32, i32, i32),
    Missing,
}

// + method syntax
fn method_explain() {
    // chaining method calls
    let c = Circle { x: 0.0, y: 0.0, radius: 2.0 };
    let d = c.grow(2.0).area();
    // associated function as constructor
    let c = Circle::new(0.0, 0.0, 2.0);
}

struct Circle {
    x: f64,
    y: f64,
    radius: f64,
}

// + + implement block
impl Circle {
    fn reference(&self) {
       println!("taking self by reference!");
    }

    fn mutable_reference(&mut self) {
       println!("taking self by mutable reference!");
    }
}

// + + multiple implement block
impl Circle {
    fn takes_ownership(self) {
       println!("taking ownership of self!");
    }
}

// + + chaining method call
impl Circle {
    fn area(&self) -> f64 {
        std::f64::consts::PI * (self.radius * self.radius)
    }

    fn grow(&self, increment: f64) -> Circle {
        Circle { x: self.x, y: self.y, radius: self.radius + increment }
    }
}

// + + associated function
impl Circle {
    fn new(x: f64, y: f64, radius: f64) -> Circle {
        Circle {
            x: x,
            y: y,
            radius: radius,
        }
    }
}
// can implement ?builder pattern with this

// + vector
fn vector_explain() {
    // + + literal
    let v = vec![1, 2, 3, 4, 5]; // v: Vec<i32>
    // + + repeating
    let v = vec![0; 10]; // ten zeroes
    // + + accessing elements
    let i: usize = 3;
    println!("The {} element of v is {}", i, v[i]);
    // + + out of bound panic
    match v.get(7) {
        Some(x) => println!("Item 7 is {}", x),
        None => println!("Sorry, this vector is too short.")
    }
    // also : get_mut(i32)
    // + + iterating
    let mut v = vec![1, 2, 3, 4, 5];
    for i in &v {
        println!("A reference to {}", i);
    }
    for i in &mut v {
        println!("A mutable reference to {}", i);
    }
    for i in v {
        println!("Take ownership of the vector and its element {}", i);
    }
}

// + string
