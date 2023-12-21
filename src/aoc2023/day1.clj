;;
;; Day 1
(ns aoc2023.day1
   (:require [clojure.string :as s]
             [aoc2023.util :as u]
             [clojure.set :as cset]
             [clojure.pprint :as p]
            ))
;; ----------------------------------------
;; programming examples
;; ----------------------------------------

(def example-input-a "src/aoc2023/day1-example-input-a.txt")
(def example-input-b "src/aoc2023/day1-example-input-b.txt")
(def sample-a (slurp example-input-a))
(def sample-b (slurp example-input-b))

;; ----------------------------------------
;; puzzle input
;; ----------------------------------------
(def puzzle-input "src/aoc2023/day1-input.txt")
(def data-a (slurp puzzle-input))

;; ----------------------------------------
;; helpers
;; ----------------------------------------

(def digitnames
  {
   "one" 1
   "two" 2
   "three" 3
   "four" 4
   "five" 5
   "six" 6
   "seven" 7
   "eight" 8
   "nine" 9 })

(def digits
  {
   "1" 1
   "2" 2
   "3" 3
   "4" 4
   "5" 5
   "6" 6
   "7" 7
   "8" 8
   "9" 9
   })

(def all-digits (cset/union digitnames digits))

;; the regexp search pattern for either digits or names of digits
(def pattern
  (re-pattern
       (str "(?=(" (s/join  "|" (keys all-digits)) "))" )))

(defn vec-to-digit
  "converts a vector or str-digits or fully written words like eight into a seq of number-digits: ['1' 'two'] -> [1 2]"
  [v]
  (mapv all-digits v))

;; --------------------------------------------------


(defn is-digit-value? [x]
  (and (>= x 0) (<= x 9)))

(defn make-number [v]
  "creates a number from first and last element of v, both need to be digits"
  (let [res (+ (* (first v) 10) (last v))]
;;    (println (str v "\t\t" res))
    res))

(defn part-a [x]
  (->> x
       (s/split-lines)
       (mapv #(mapv u/char-to-int %1))
       (mapv (partial filter is-digit-value?))
       (mapv make-number)
       (reduce + 0)))

;; --------------------------------------------------
;; Part b - interpreting strings as numbers
;; funny... eightwo is 82... difficult with regex! 
;; --------------------------------------------------

(defn parse-line [x]
  (into []
        (remove empty? (flatten (re-seq pattern x))))
  )

(defn part-b [x]
  (->> x
       (s/split-lines)
       (mapv parse-line)
       (mapv vec-to-digit)
       (mapv make-number)
       (reduce + 0)
       )
  )


