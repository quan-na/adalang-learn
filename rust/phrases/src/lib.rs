#[cfg(test)]
mod tests {
    #[test]
    fn it_works() {
    }
}

// + module definitions
// + + single file contain multiple modules
// + + + make everything public with pub
pub mod english {
    pub mod greetings {
        pub fn hello() -> String {
            "Hello!".to_string()
        }
    }
    pub mod farewells {
        pub fn goodbye() -> String {
            "Goodbye.".to_string()
        }
    }
}

// + + by using this syntax, cargo will expect src/japanese.rs or src/japanese/mod.rs
// + + + mod.rs is expected if this module has sub-modules
// + + + if it contains no sub-modules, japanese.rs is expected
mod japanese;
