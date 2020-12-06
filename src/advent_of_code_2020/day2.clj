(ns advent-of-code-2020.day2
  (:require [clojure.string :as string]
            [clojure.java.io :as io]))

(defn- valid-pwd1? [[low hi char pwd]]
  (<= (read-string low) (count (re-seq (re-pattern char) pwd)) (read-string hi)))

(defn- xor [a b] (and (or a b) (not (and a b))))

(defn- valid-pwd2? [[low hi char pwd]]
  (xor (= (nth pwd (dec (read-string low))) (first char)) (= (nth pwd (dec (read-string hi))) (first char))))

(defn- parse-line [str]
  (string/split (string/replace str #"[\:\-]" " ") #" +"))

(defn part1 [file]
  (count (filter valid-pwd1? (map parse-line (string/split-lines (slurp file))))))

(defn part2 [file]
  (count (filter valid-pwd2? (map parse-line (string/split-lines (slurp file))))))


(defn -main [] (let [f (io/resource "in_day2.txt")]
                 (println "Part 1:" (part1 f) "\nPart 2:" (part2 f))))