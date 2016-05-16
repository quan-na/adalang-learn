(ns clojure-noob.macro-explain)

;; a simple macro
(defmacro backwards
  [form]
  (reverse form))
(backwards (" backwards" " am" "I" str))
;; => "I am backwards"

;; evaluation a list
(def addition-list (list + 1 2))
(eval addition-list)
;; => 3
(eval (concat addition-list [10]))
;; => 13
(eval (list 'def 'lucky-number (concat addition-list [10])))
;; => #'user/lucky-number

;; read a string to list
(read-string "(+ 1 2)")
;; => (+ 1 2)
(list? (read-string "(+ 1 2)"))
;; => true
(conj (read-string "(+ 1 2)") :zagglewag)
;; => (:zagglewag + 1 2)

;; read macros
(read-string "'(a b c)")
;; => (quote (a b c))
(read-string "@var")
;; => (clojure.core/deref var)
(read-string "; ignore!\n(+ 1 2)")
;; => (+ 1 2)

;; things that evaluated to itself
true
;; => true
false
;; => false
{}
;; => {}
:huzzah
;; => :huzzah
()
;; => ()

(defmacro ignore-last-operand
  [function-call]
  (butlast function-call))
(ignore-last-operand (+ 1 2 10))
;; => 3
(ignore-last-operand (+ 1 2 (println "look at me!!!")))
;; => 3

;; data structures returned by functions are not evaluated
(macroexpand '(ignore-last-operand (+ 1 2 (println "look at me!!!"))))
;; => (+ 1 2)

(defn read-resource
  "Read a resource into a string"
  [path]
  (read-string (slurp (clojure.java.io/resource path))))
(defn read-resource
  [path]
  (-> path
      clojure.java.io/resource
      slurp
      read-string))

;; define macro syntax
(defmacro cloned-and
  "Evaluates expressions one at a time, from left to right. If a form
  returns logical false (nil or false), and returns that value and
  doesn't evaluate any of the other expressions, otherwise it returns
  the value of the last expression. (and) returns true."
  {:added "1.0"}
  ([] true)
  ([x] x)
  ([x & next]
   `(let [and# ~x]
      (if and# (and ~@next) and#))))

;; quoting symbols
(defmacro my-print
  [expression]
  (list 'let ['result expression]
        (list 'println 'result)
        'result))

;; syntax quoting
`+
;; => clojure.core/+
`(+ 1 2)
;; => (clojure.core/+ 1 2)

;; kryptonite of quoting
`(+ 1 ~(inc 1))
;; => (clojure.core/+ 1 2)
`(+ 1 (inc 1))
;; => (clojure.core/+ 1 (clojure.core/inc 1))

;; unquote slicing
`(+ ~@(list 1 2 3))
;; => (clojure.core/+ 1 2 3)
