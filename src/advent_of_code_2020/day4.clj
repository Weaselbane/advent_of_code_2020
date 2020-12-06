(ns advent-of-code-2020.day4
  (:require [clojure.string :as string]
            [clojure.java.io :as io]
            [clojure.edn :as edn]))

(defn- verify1 [ppt]
  (every? (partial contains? ppt) ["byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"]))

(defn- verify2 [{byr "byr", iyr "iyr", eyr "eyr", hgt "hgt", hcl "hcl", ecl "ecl", pid "pid"}]
  (every? true? [
                 (if (re-matches #"^[0-9]{4}$" byr) (<= 1920 (edn/read-string byr) 2002))
                 (if (re-matches #"^[0-9]{4}$" iyr) (<= 2010 (edn/read-string iyr) 2020))
                 (if (re-matches #"^[0-9]{4}$" eyr) (<= 2020 (edn/read-string eyr) 2030))
                 (if (re-matches #"^[0-9]{3}cm$" hgt) (<= 150 (edn/read-string (subs hgt 0 3)) 193)
                   (if (re-matches #"^[0-9]{2}in$" hgt) (<= 59 (edn/read-string (subs hgt 0 2)) 76)))
                 (boolean (re-matches #"^\#[0-9a-f]{6}$" hcl))
                 (boolean (re-matches #"^(amb|blu|brn|gry|grn|hzl|oth)$" ecl))
                 (boolean (re-matches #"^[0-9]{9}$" pid))
                 ]))

(defn- parse-ppt [ppt-str]
  (into (hash-map)
        (map
          #(string/split % #":")
             (string/split ppt-str #"[ \n]|\r\n"))
        )
  )

(defn part1 [file]
  (count (filter (comp verify1 parse-ppt) (string/split (slurp file) #"\r?\n\r?\n"))))

(defn part2 [file]
  (count (filter #(if (verify1 %) (verify2 %)) (map parse-ppt (string/split (slurp file) #"\r?\n\r?\n")))))


(defn -main [] (let [f (io/resource "in_day4.txt")]
                 (println "Part 1:" (part1 f) "\nPart 2:" (part2 f))))