(require '[clojure.string :as str])
(require '[clojure.set :as set])
(def read_input (map #(str/split % #"," ) (str/split (slurp "input") #"\n")))
(defn up [A B] (assoc A 1 (+ (A 1) B) ))
(defn down [A B] (assoc A 1 (- (A 1) B) ))
(defn left [A B] (assoc A 0 (- (A 0) B) ))
(defn right [A B] (assoc A 0 (+ (A 0) B) ))

(defn expand_segment [start magnitude func] (map #(func start %) (range 1 (inc magnitude)) ) )

(defn expand_wire [wire] 
  (def funcmap {\R right \D down \L left \U up})
  (loop [acc 1 ret [[0 0]] ] 
    (if (> acc (count wire)) 
      ret 
      (recur (inc acc) (concat ret (expand_segment (last ret)  (read-string (str/join "" ( rest (wire (dec acc))))) (funcmap (first (wire (dec acc)) ))))))  
))

(defn intersections [wires] 
  (set/intersection (set (expand_wire (wires 0))) (set (expand_wire (wires 1))))
)
(defn sol_a [input] 
  (def wires (into [] input))
  (first (rest (sort (map #(+ (Math/abs (first %1)) (Math/abs (last %1)) ) (intersections wires)))))
)
(print "3a: ")
(println (sol_a read_input))

(defn steps [expanded intr] (keep-indexed #(when (=  % intr)) expanded))


