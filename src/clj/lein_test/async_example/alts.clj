(ns lein-test.async-example.alts
  (:require [clojure.core.async :refer [alts! chan go timeout >! <!!]]))

(defn test-alts []
  (let [c (chan)]
    (go
     (Thread/sleep (rand-int 1000))
     (>! c "Success!"))
    (<!!
     (go
      (let [[_ source] (alts! [c (timeout 500)])]
        (if (= source c)
          (println "Got a value!")
          (println "Timeout!")))))))
