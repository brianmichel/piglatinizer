(ns piglatinizer.core-test
  (:require [clojure.test :refer :all]
            [piglatinizer.core :refer :all]))

(deftest latin-singular-test
  (testing "Tests to verify a singular word is latinized"
    (is (= "appyhay" (pig-latinize-word  "happy")))))

(deftest latin-multiple-test
  (testing "Verify an entire sentence is properly latinized"
    (is (= "otallytay awesomeway hingtay" (pig-latinize "totally awesome thing")))))

(deftest vowels-are-identified
  (testing "Vowels should be properly identified."
    (is (= true (vowel-in-position 0 "awesoem")))
    (is (= false (vowel-in-position 2 "lawful")))))

(deftest pig-rule-validation
  (testing "Pig latin rules should be properly enforces"
    (is (= "awesomeway" (pig-latinize "awesome")))
    (is (= "ayday" (pig-latinize "day")))))

(deftest de-pig-rule-validation
  (testing "Going from pig latin should properly enforce rules"
    (is (= "awesome" (de-pig-latinize "awesomeway")))
    (is (= "day" (de-pig-latinize "ayday")))))

(deftest lost-in-translation
  (testing "Nothing should be lost in translation to or from pig latin"
    (let [original "an awesome day is just a mile away"]
      (is (= original (de-pig-latinize (pig-latinize original)))))))