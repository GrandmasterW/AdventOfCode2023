;;
;; Day 1
(ns aoc2023.day1
   (:require [clojure.string :as s]
             [aoc2023.util :as u]
            ))
;; ----------------------------------------
;; puzzle examples
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


;; --------------------------------------------------

(defn make-pattern
  "creates a pattern with lookahead (if true) for the keys of the map"
  [the-digits-map lookahead]
  (if lookahead
    (re-pattern
     (str "(?=("
          (s/join  "|" (keys the-digits-map)) "))"))
    (re-pattern
     (s/join  "|" (keys the-digits-map)))))

(defn vec-to-digit
  "converts a vector or str-digits or fully written words like eight into a seq of number-digits: ['1' 'two'] -> [1 2] by using the given hashmap"
  [the-digits v]
  (mapv the-digits v))

;; --------------------------------------------------


(defn is-digit-value?
  "true if x is between 0 and 9" [x]
  (and (>= x 0) (<= x 9)))

(defn make-number 
  "creates a number from first and last element of v, both need to be digits"
  [v]
  (+ (* (first v) 10) (last v)))

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

(defn parse-line
  "parse a string x by regex pattern matching with lookahead to comply with eightwo problem. Removes the not required empty elements after flattening it all. Returns a vector of strings."
  [regpattern x]
  (into []
        (remove empty? (flatten (re-seq regpattern x)))))


(defn part-b [x]
  (let [all-digits (merge digitnames digits) ; all we need is strings as keys
        pattern (make-pattern all-digits true) ; regexp search pattern for either digits or names of digits
      ]
    (->> x
         (s/split-lines)
         (mapv (partial parse-line pattern))
         (mapv (partial vec-to-digit all-digits))
         (mapv make-number)
         (reduce + 0))))


