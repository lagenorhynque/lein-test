(ns lein-test.monad-example.rpn-calc
  (:require [clojure.string :as str]
            [cats.core :as m]
            [cats.monad.either :as either]
            [cats.monad.maybe :as maybe]))

;;; without monads

(defn- folding-function [[x y & ys :as xs] s]
  (cond
    (and x y (= s "*")) (conj ys (* y x))
    (and x y (= s "+")) (conj ys (+ y x))
    (and x y (= s "-")) (conj ys (- y x))
    :else (conj xs (Double/parseDouble s))))

(defn solve-rpn [s]
  (as-> s v
    (str/split v #"\s+")
    (reduce folding-function () v)
    (first v)))

;;; with maybe monad

(defn- read-maybe [s]
  (try
    (maybe/just (Double/parseDouble s))
    (catch NumberFormatException _
      (maybe/nothing))))

(defn folding-with-maybe [[x y & ys :as xs] s]
  (cond
    (and x y (= s "*")) (maybe/just (conj ys (* y x)))
    (and x y (= s "+")) (maybe/just (conj ys (+ y x)))
    (and x y (= s "-")) (maybe/just (conj ys (- y x)))
    :else ((m/lift-m 1 #(conj xs %))
           (read-maybe s))))

(defn solve-rpn' [folding-function s]
  (m/mlet [result (m/foldm folding-function
                           ()
                           (str/split s #"\s+"))
           :when (= (count result) 1)]
    (m/return (first result))))

;;; with either monad

(defn- read-either [s]
  (try
    (either/right (Double/parseDouble s))
    (catch NumberFormatException _
      (either/left "invalid input"))))

(defn folding-with-either [[x y & ys :as xs] s]
  (cond
    (and x y (= s "*")) (either/right (conj ys (* y x)))
    (and x y (= s "+")) (either/right (conj ys (+ y x)))
    (and x y (= s "-")) (either/right (conj ys (- y x)))
    :else ((m/lift-m 1 #(conj xs %))
           (read-either s))))
