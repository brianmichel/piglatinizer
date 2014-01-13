(use '[clojure.string :only (join split)])

(ns ^{ :doc "Totally awesome pig latinizer
A library that will allow the consumer to pass
in a regular string, and receive a version of
the same string which complies with the rules
of the pig latin substitution."
       :author "Brian Michel (brian.michel@gmail.com)"}
    piglatinizer.core)

(defn vowel-in-position
  "Returns true if a vowel exists in the desired position"
  [position word]
  (let [character (.toLowerCase (subs word position (inc position)))]
    (contains? #{"a" "e" "i" "o" "u"} character)))

(defn- pig-interpret
  "Interpreter for translating to/from pig latin"
  [sentence function]
  (clojure.string/join " "
     (map function
          (clojure.string/split sentence #"\s"))))

(defn de-pig-latinize-word
  "Given a pig latinized word, return it's regular version"
  [word]
  (let [split (split-at (- (count word) 3) word) beginning (first split) ending (last split)]
    (if (= (first ending) \w)
      (clojure.string/join beginning)
      (do
        (clojure.string/join "" (cons (first ending) beginning))))))

(defn pig-latinize-word
  "Pig latinize a given word."
  [word]
  (if (vowel-in-position 0 word)
    (do
      (str word "way"))
    (do
      (let [[first & rest] word]
        (str (clojure.string/join "" rest) first "ay")))))

(defn de-pig-latinize
  "De-pig latinize an entire string."
  [sentence]
  (pig-interpret sentence de-pig-latinize-word))

(defn pig-latinize
  "Pig latinize an entire string."
  [sentence]
  (pig-interpret sentence pig-latinize-word))