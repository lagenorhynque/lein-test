(ns lein-test.stm-example.nonstm-demo)

(def ^:private n (atom 0))

(defn- f [x]
  (* x 2))

(defn exec [x]
  (reset! n x)
  (swap! n f))
