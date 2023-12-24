(ns aoc2023.day1-test
  (:require [clojure.test :refer :all]
            [aoc2023.day1 :as day]))

;; --------------------------------------------------

(def all-digits (merge day/digitnames day/digits))
(def pattern (day/make-pattern all-digits true))

(deftest vec-to-digit-test
  (testing "simple cases"
    (is (= [1 2 3]
           (day/vec-to-digit all-digits ["1" "two" "three"])))
    (is (= [1 2 5 3 4]
           (day/vec-to-digit all-digits ["1" "two" "5" "three" "4"])))
    )
  (testing "all elements"
    (is (= [1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9]
           (day/vec-to-digit all-digits ["1" "2" "3" "4" "5" "6" "7" "8" "9"
                              "one"
                              "two"
                              "three"
                              "four"
                              "five"
                              "six"
                              "seven"
                              "eight"
                              "nine"]))))
  (testing "overlapping words eightwo"
    (is (= [1 8 2 1 4]
           (day/vec-to-digit all-digits (day/parse-line pattern "1eightwone4")))))
  )



(def problems {
               "seightwoone8qxcfgszninesvfcnxc68" [8 2 1 8 9 6 8]})

;; --------------------------------------------------

(def sample-result-a 142)
(def sample-result-b 281)
(def result-a 52974)
(def result-b 53340)

(deftest part-a-sample-test
  (testing "sample to result"
    (is (= (day/part-a day/sample-a)
           sample-result-a))))

(deftest part-a-real-test
  (testing "real data to result"
    (is (= (day/part-a day/data-a)
           result-a))))

;; --------------------------------------------------

(deftest part-b-sample-test
  (testing "sample to result"
    (is (= (day/part-b day/sample-b)
           sample-result-b))))


(deftest part-b-real-test
  (testing "real data to result"
    (is (= (day/part-b day/data-a)
           result-b))))

