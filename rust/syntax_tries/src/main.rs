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
    // + + + ?destructure
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
    // + + ?elseif
    if x == 5 {
        println!( "Thank god!" );
    } else if x == 6 {
        println!( "Thank flying spagetty!" );
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
    let mut done = false; // mut done: bool

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
