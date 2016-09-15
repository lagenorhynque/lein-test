(ns lein-test.stm-example.reminders)

(defn- set-reminder [s]
  (let [t (Integer/parseInt s)]
    (printf "OK, I'll remind you in %d seconds\n" t)
    (Thread/sleep (* 1000 t))
    (printf "%d seconds is up!\n" t)))

(defn exec []
  (loop [s (read-line)]
    (when (= s "exit")
      (let [t (Thread. #(set-reminder s))]
        (.start t)
        (.join t)
        (recur (read-line))))))
