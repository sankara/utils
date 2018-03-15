(ns config)

(def config
  (clojure.edn/read-string (slurp (clojure.string/join (System/getProperty "file.separator") [(System/getProperty "user.home") ".clj-settings"]))))
