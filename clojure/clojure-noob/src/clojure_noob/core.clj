(ns clojure-noob.core
  (:gen-class))

;; to run this inside a repl
;; => (-main)
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")

  ;; form? :: valid code
  ;; - literal
  1
  "a string"
  [1 "vector" "of" "string"]

  ;; - operations
  (+ 1 2 3)
  (str "It was the panda " "in the library " "with a dust buster")

  ;; control flow?
  ;; - if
  (if true
    "By Zeus's hammer!"
    "By Aquaman's trident!")

  ;; unspecified branch return nil
  (if true
    "By Zeus's hammer!")

  ;; with do blocks
  (if true
    (do (println "Success!")
        "By Zeus's hammer!")
    (do (println "Failure!")
        "By Aquaman's trident!"))

  ;; - when
  (when true
    (println "Success!")
    "abra cadabra")

  ;; - nil?
  (nil? 1) ; => false

  (nil? nil) ; => true

  (if "bears eat beets"
    "bears beets Battlestar Galactica") ; => "bears beets Battlestar Galactica"

  (if nil
    "This won't be the result because nil is falsey"
    "nil is false") ; => "nil is false"

  ;; =
  (= 1 1) ; => true

  (= nil nil) ; => true

  (or (= 0 1) (= "yes" "no")) ; => false

  (or false nil :large_I_mean_venti :why_cant_I_just_say_large) ; => :large_I_mean_venti

  (and :free_wifi :hot_coffee) ; => :hot_coffee

  (and :feelin_super_cool nil false) ; => nil

  ;; - define variable
  (def failed-protagonist-names
    ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"])

  ;; number
  (def a-integer 93)
  (def a-real 1.2)
  (def a-factional 1/5)

  ;; string
  (def a-string "a string")

  ;; map
  (def empty-map {})

  (def big-map
    {:first-name "Charlie"
     :last-name "McFishwich"
     "string-key" +
     :name {:first "John"
            :middle "Jacob"
            :last "Jingleheimerschmidt"}}
    )

  ;; hash-map function
  (hash-map :a 1 :b 2)

  ;; get function
  (get {:a 0 :b {:c "ho hum"}} :b)

  (get {:a 0 :b 1} :c) ; => nil

  (get {:a 0 :b 1} :c "unicorns?") ; => "unicorns?"

  (get-in {:a 0 :b {:c "ho hum"}} [:b :c]) ; => "ho hum"

  ;; map as function
  ({:name "The Human Coffeepot"} :name) ; => "The Human Coffeepot"

  ;; - keyword
  :a
  :rumplestiltsken
  :34
  :_?

  ;; as function
  (:a {:a 1 :b 2 :c 3}) ; => 1

  ;; default value
  (:d {:a 1 :b 2 :c 3} "No gnome knows homes like Noah knows") ; => "No gnome knows homes like Noah knows"

  ;; - vector
  [3 2 1]

  ;; get
  (get ["a" {:name "Pugsley Winterbottom"} "c"] 1) ; => {:name "Pugsley Winterbottom"}

  (vector "creepy" "full" "moon") ; => ["creepy" "full" "moon"]

  (conj [1 2 3] 4) ; => [1 2 3 4]

  ;; list
  '(1 2 3 4) ; => (1 2 3 4)

  (nth '(:a :b :c) 0) ; => :a

  (conj '(1 2 3) 4) ; => (4 1 2 3)

  ;; set
  #{"kurt vonnegut" 20 :icicle}

  (hash-set 1 1 2 2) ; => #{1 2}

  (conj #{:a :b} :b) ; => #{:a :b}

  (set [3 3 3 4 4]) ; => #{3 4}

  (contains? #{:a :b} :a) ; => true

  (:a #{:a :b}) ; => :a

  (get #{:a :b} :a) ; => :a

  (get #{:a :b} "kurt vonnegut") ; => nil

  ;; - function
  ((or + -) 1 2 3)

  (map inc [0 1 2 3]) ; => (1 2 3 4)

  (defn too-enthusiastic ; function name
    "Return a cheer that might be a bit too enthusiastic" ; document string
    [name] ; arguments
    (str "OH. MY. GOD! " name " YOU ARE MOST DEFINITELY LIKE THE BEST "
         "MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE")) ; function body

  ;; multiple arity
  (defn multi-arity
    ;; 3-arity arguments and body
    ([first-arg second-arg third-arg]
     (+ first-arg second-arg third-arg))
    ;; 2-arity arguments and body
    ([first-arg second-arg]
     (+ first-arg second-arg))
    ;; 1-arity arguments and body
    ([first-arg]
     (+ first-arg)))

  ;; default parameter
  (defn x-chop
    "Describe the kind of chop you're inflicting on someone"
    ([name chop-type]
     (str "I " chop-type " chop " name "! Take that!"))
    ([name]
     (x-chop name "karate")))

  ;; parameter list with &
  (defn codger-communication
    [whippersnapper]
    (str "Get off my lawn, " whippersnapper "!!!"))
  (defn codger
    [& whippersnappers]
    (map codger-communication whippersnappers))
  (codger "Billy" "Anne-Marie" "The Incredible Bulk")
  ;; => ("Get off my lawn, Billy!!!"
  ;;     "Get off my lawn, Anne-Marie!!!"
  ;;     "Get off my lawn, The Incredible Bulk!!!")

  ;; matching items at beginning of the list
  (defn favorite-things
    [name & things]
    (str "Hi, " name ", here are my favorite things: "
         (clojure.string/join ", " things)))
  (favorite-things "Doreen" "gum" "shoes" "kara-te") ; => "Hi, Doreen, here are my favorite things: gum, shoes, kara-te"

  ;; Return the first element of a collection
  (defn my-first
    [[first-thing]] ; Notice that first-thing is within a vector
    first-thing)
  (my-first ["oven" "bike" "war-axe"]) ; => "oven"

  ;; matching multiple items at beginning of the collection
  (defn chooser
    [[first-choice second-choice & unimportant-choices]]
    (println (str "Your first choice is: " first-choice))
    (println (str "Your second choice is: " second-choice))
    (println (str "We're ignoring the rest of your choices. "
                  "Here they are in case you need to cry over them: "
                  (clojure.string/join ", " unimportant-choices))))

  (chooser ["Marmalade", "Handsome Jack", "Pigpen", "Aquaman"])
  ;; => Your first choice is: Marmalade
  ;; => Your second choice is: Handsome Jack
  ;; => We're ignoring the rest of your choices. Here they are in case you need to cry over them: Pigpen, Aquaman

  ;; map pattern matching
  (defn announce-treasure-location
    [{lat :lat lng :lng}]
    (println (str "Treasure lat: " lat))
    (println (str "Treasure lng: " lng)))
  (announce-treasure-location {:lat 28.22 :lng 81.33})
  ;; => Treasure lat: 100
  ;; => Treasure lng: 50

  ;; matching with key same name as variable
  (defn steer-ship! [loc] loc)
  (defn receive-treasure-location
    [{:keys [lat lng] :as treasure-location}]
    (println (str "Treasure lat: " lat))
    (println (str "Treasure lng: " lng))
    ;; One would assume that this would put in new coordinates for your ship
    (steer-ship! treasure-location))

  ;; anonymous function
  (map (fn [name] (str "Hi, " name))
       ["Darth Vader" "Mr. Magoo"])
  ;; => ("Hi, Darth Vader" "Hi, Mr. Magoo")
  ((fn [x] (* x 3)) 8)
  ;; => 24

  ;; used with define -> as replacement for defn
  (def my-special-multiplier (fn [x] (* x 3)))

  ;; even shorter syntax
  (#(* % 3) 8)
  ;; => 24
  (map #(str "Hi, " %)
     ["Darth Vader" "Mr. Magoo"])
  ;; => ("Hi, Darth Vader" "Hi, Mr. Magoo")
  (#(str %1 " and " %2) "cornbread" "butter beans")
  ;; => "cornbread and butter beans"
  (#(identity %&) 1 "blarg" :yip)
  ;; => (1 "blarg" :yip)

  ;; return a function (closure)
  (defn inc-maker
  "Create a custom increment function"
    [inc-by]
    #(+ % inc-by))
  (def inc3 (inc-maker 3))
  (inc3 7)
  ;; => 10

  ;; 'let' start new scope
  (def x 0)
  (let [x 1] x)
  ;; => 1

  ;; use with pattern matching
  (def dalmatian-list
    ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])
  (let [[pongo & dalmatians] dalmatian-list]
    [pongo dalmatians])
  ;; => ["Pongo" ("Perdita" "Puppy 1" "Puppy 2")]

  ;; 'loop' is not-so-short syntax for recursive
  (loop [iteration 0]
    (println (str "Iteration " iteration))
    (if (> iteration 3)
      (println "Goodbye!")
      (recur (inc iteration))))
  ;; => Iteration 0
  ;; => Iteration 1
  ;; => Iteration 2
  ;; => Iteration 3
  ;; => Iteration 4
  ;; => Goodbye!

  ;; regular expression
  (re-find #"^left-" "left-eye")
  ;; => "left-"

  (defn matching-part
    [part]
    {:name (clojure.string/replace (:name part) #"^left-" "right-")
     :size (:size part)})
  (matching-part {:name "left-eye" :size 1})
  ;; => {:name "right-eye" :size 1}]

  ;; reduce
  ;; sum with reduce
  (reduce + [1 2 3 4])
  ;; => 10

  ;; structure response to first, rest, and cons will response to sequential functions
  (defn titleize
    [topic]
    (str topic " for the Brave and True"))
  (map titleize ["Hamsters" "Ragnarok"])
  ;; => ("Hamsters for the Brave and True" "Ragnarok for the Brave and True")
  (map titleize '("Empathy" "Decorating"))
  ;; => ("Empathy for the Brave and True" "Decorating for the Brave and True")
  (map titleize #{"Elbows" "Soap Carving"})
  ;; => ("Elbows for the Brave and True" "Soap Carving for the Brave and True")
  (map #(titleize (second %)) {:uncomfortable-thing "Winking"})
  ;; => ("Winking for the Brave and True")

  ;; convert any collection to sequences
  (seq '(1 2 3))
  ;; => (1 2 3)
  (seq [1 2 3])
  ;; => (1 2 3)
  (seq #{1 2 3})
  ;; => (1 2 3)
  (seq {:name "Bill Compton" :occupation "Dead mopey guy"})
  ;; => ([:name "Bill Compton"] [:occupation "Dead mopey guy"])

  ;; map
  (map inc [1 2 3])
  ;; => (2 3 4)
  (map str ["a" "b" "c"] ["A" "B" "C"])
  ;; => ("aA" "bB" "cC")

  ;; reduce
  (reduce (fn [new-map [key val]]
            (assoc new-map key (inc val)))
          {}
          {:max 30 :min 10})
  ;; => {:max 31, :min 11}

  ;; take, drop, take-while, and drop-while
  (take 3 [1 2 3 4 5 6 7 8 9 10])
  ;; => (1 2 3)
  (drop 3 [1 2 3 4 5 6 7 8 9 10])
  ;; => (4 5 6 7 8 9 10)
  (def food-journal
    [{:month 1 :day 1 :human 5.3 :critter 2.3}
     {:month 1 :day 2 :human 5.1 :critter 2.0}
     {:month 2 :day 1 :human 4.9 :critter 2.1}
     {:month 2 :day 2 :human 5.0 :critter 2.5}
     {:month 3 :day 1 :human 4.2 :critter 3.3}
     {:month 3 :day 2 :human 4.0 :critter 3.8}
     {:month 4 :day 1 :human 3.7 :critter 3.9}
     {:month 4 :day 2 :human 3.7 :critter 3.6}])
  (take-while #(< (:month %) 3) food-journal)
  ;; => ({:month 1 :day 1 :human 5.3 :critter 2.3}
  ;;      {:month 1 :day 2 :human 5.1 :critter 2.0}
  ;;      {:month 2 :day 1 :human 4.9 :critter 2.1}
  ;;      {:month 2 :day 2 :human 5.0 :critter 2.5})
  (drop-while #(< (:month %) 3) food-journal)
  ;; => ({:month 3 :day 1 :human 4.2 :critter 3.3}
  ;;      {:month 3 :day 2 :human 4.0 :critter 3.8}
  ;;      {:month 4 :day 1 :human 3.7 :critter 3.9}
  ;;      {:month 4 :day 2 :human 3.7 :critter 3.6})

  ;; filter
  (filter #(< (:human %) 5) food-journal)
  ;; => ({:month 2 :day 1 :human 4.9 :critter 2.1}
  ;;      {:month 3 :day 1 :human 4.2 :critter 3.3}
  ;;      {:month 3 :day 2 :human 4.0 :critter 3.8}
  ;;      {:month 4 :day 1 :human 3.7 :critter 3.9}
  ;;      {:month 4 :day 2 :human 3.7 :critter 3.6})

  ;; some
  (some #(> (:critter %) 5) food-journal)
  ;; => nil
  (some #(> (:critter %) 3) food-journal)
  ;; => true

  )
