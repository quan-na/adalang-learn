(ns clojure-noob.concurency)

;; the future macro
(future (Thread/sleep 4000)
        (println "I'll print after 4 seconds"))
(println "I'll print immediately")

;; refer to a future
(let [result (future (println "this prints once")
                     (+ 1 1))]
  (println "deref: " (deref result))
  (println "@: " @result))

;; refer to a future will force the thread to wait
(let [result (future (Thread/sleep 3000)
                     (+ 1 1))]
  (println "The result is: " @result)
  (println "It will be at least 3 seconds before I print"))
;; => The result is: 2
;; => It will be at least 3 seconds before I print

;; specify how long it will wait
(deref (future (Thread/sleep 1000) 0) 10 5)
;; => 5

;; check if a future is ready
(realized? (future (Thread/sleep 1000)))
;; => false
(let [f (future)]
  @f
  (realized? f))
;; => true

;; delay
(def jackson-5-delay
  (delay (let [message "Just call my name and I'll be there"]
           (println "First deref:" message)
           message)))
(force jackson-5-delay)
;; => First deref: Just call my name and I'll be there
;; => "Just call my name and I'll be there"
@jackson-5-delay
;; => "Just call my name and I'll be there"

;; promise
(def my-promise (promise))
(deliver my-promise (+ 1 2))
@my-promise
;; => 3

;; define an atom
(def fred (atom {:cuddle-hunger-level 0
                 :percent-deteriorated 0}))

;; de-reference an atom
(let [zombie-state @fred]
  (if (>= (:percent-deteriorated zombie-state) 50)
    (future (println (:cuddle-hunger-level zombie-state)))))

;; modify atom 's value using swap!
(swap! fred
       (fn [current-state]
         (merge-with + current-state {:cuddle-hunger-level 1})))
;; with additional parameter
(defn increase-cuddle-hunger-level
  [zombie-state increase-by]
  (merge-with + zombie-state {:cuddle-hunger-level increase-by}))
(swap! fred increase-cuddle-hunger-level 10)
;; => {:cuddle-hunger-level 12, :percent-deteriorated 1}
@fred
;; => {:cuddle-hunger-level 12, :percent-deteriorated 1}

