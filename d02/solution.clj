; IO missing, but good enough
(defn sol_a [noun verb]
  (def input [1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,13,19,1,9,19,23,1,6,23,27,2,27,9,31,2,6,31,35,1,5,35,39,1,10,39,43,1,43,13,47,1,47,9,51,1,51,9,55,1,55,9,59,2,9,59,63,2,9,63,67,1,5,67,71,2,13,71,75,1,6,75,79,1,10,79,83,2,6,83,87,1,87,5,91,1,91,9,95,1,95,10,99,2,9,99,103,1,5,103,107,1,5,107,111,2,111,10,115,1,6,115,119,2,10,119,123,1,6,123,127,1,127,5,131,2,9,131,135,1,5,135,139,1,139,10,143,1,143,2,147,1,147,5,0,99,2,0,14,0])
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
