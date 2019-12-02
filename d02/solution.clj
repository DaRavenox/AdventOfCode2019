(require '[clojure.string :as str])
(def read_input (map read-string (str/split (slurp "input") #",")))
(def input (zipmap (range (count read_input)) read_input))

(defn sol_a [noun verb]
  (def funcmap {1 +, 2 *})
  (loop [n 0 res (assoc (assoc input 1 noun) 2 verb)]
    (if (= (res (* 4 n)) 99)
      res
      (recur (inc n)
             (assoc res
                    (res (+ 3 (* 4 n)))
                    ((funcmap (res (* 4 n)))
                     (res  (res (+ 1 (* 4 n))))
                     (res  (res (+ 2 (* 4 n))))))))))

(print "2a: ")
(print ((sol_a 12 2) 0))
(println "")


; This is not clojurelike, hence UGLY.
(defn sol_b_non_clojurelike [result]
  (doseq [a (range 100)
          b (range 100)]
    (if  (== result ((sol_a a b) 0)) (print (+ (* 100 a) b)))))

;yessir this here is what I want to see.
(defn help_fun [res x]
  (some #(if (== res ((sol_a % x) 0)) (not (print %))) (range 100)))

(defn sol_b [result]
  (some #(if (help_fun result %) %) (range 100)))

(print "2b: ")
(sol_b_non_clojurelike 19690720)
(println "")

(print "2b: ")
(print (sol_b 19690720))
