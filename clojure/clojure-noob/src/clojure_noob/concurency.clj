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

;; update without checking current value
(reset! fred {:cuddle-hunger-level 0
              :percent-deteriorated 0})

;; watch
(defn shuffle-speed
  [zombie]
  (* (:cuddle-hunger-level zombie)
     (- 100 (:percent-deteriorated zombie))))
(defn shuffle-alert
  [key watched old-state new-state]
  (let [sph (shuffle-speed new-state)]
    (if (> sph 5000)
      (do
        (println "Run, you fool!")
        (println "The zombie's SPH is now " sph)
        (println "This message brought to your courtesy of " key))
      (do
        (println "All's well with " key)
        (println "Cuddle hunger: " (:cuddle-hunger-level new-state))
        (println "Percent deteriorated: " (:percent-deteriorated new-state))
        (println "SPH: " sph)))))
;; add watch
(add-watch fred :fred-shuffle-alert shuffle-alert)
(swap! fred update-in [:percent-deteriorated] + 1)
;; => All's well with  :fred-shuffle-alert
;; => Cuddle hunger:  22
;; => Percent deteriorated:  3
;; => SPH:  2134
(swap! fred update-in [:cuddle-hunger-level] + 30)
;; => Run, you fool!
;; => The zombie's SPH is now 5044
;; => This message brought to your courtesy of :fred-shuffle-alert

;; atom validation
(defn percent-deteriorated-validator
  [{:keys [percent-deteriorated]}]
  (and (>= percent-deteriorated 0)
       (<= percent-deteriorated 100)))
(def bobby
  (atom
   {:cuddle-hunger-level 0 :percent-deteriorated 0}
    :validator percent-deteriorated-validator))
(swap! bobby update-in [:percent-deteriorated] + 200)
;; This throws "Invalid reference state"
