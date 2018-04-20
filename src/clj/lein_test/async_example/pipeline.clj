(ns lein-test.async-example.pipeline
  (:require [clojure.core.async :as async]))

(def ^:const interval-step-a 1000)
(def ^:const interval-step-b 1000)
(def ^:const interval-step-c 1000)
(def ^:const data-num 10)

(defn step-a [i]
  (println (str "Task: " i " stepA開始"))
  (Thread/sleep interval-step-a)
  (println (str "Task: " i " stepA終了"))
  i)

(defn step-b [i]
  (println (str "Task: " i " stepB開始"))
  (Thread/sleep interval-step-b)
  (println (str "Task: " i " stepB終了"))
  i)

(defn step-c [i]
  (println (str "Task: " i " stepC開始"))
  (Thread/sleep interval-step-c)
  (println (str "Task: " i " stepC終了"))
  i)

(defn use-pipelines []
  (let [in (async/chan)
        a->b (async/chan)
        b->c (async/chan)
        out (async/chan)
        parallelism 5
        pipeline-a (async/pipeline parallelism a->b (map step-a) in)
        pipeline-b (async/pipeline parallelism b->c (map step-b) a->b)
        pipeline-c (async/pipeline parallelism out (map step-c) b->c)]
    (async/onto-chan in (range 1 (inc data-num)))
    (loop []
      (when (async/<!! out)
        (recur)))))

(defn use-pipelines' []
  (let [in (async/chan)
        out (async/chan)
        parallelism 5
        xf (comp (map step-a)
                 (map step-b)
                 (map step-c))
        pipeline (async/pipeline parallelism out xf in)]
    (async/onto-chan in (range 1 (inc data-num)))
    (loop []
      (when (async/<!! out)
        (recur)))))
