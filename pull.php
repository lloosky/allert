<?php
$dump = var_dump($_POST);
$log = $dump.PHP_EOL;
file_put_contents('./log_'.date("j.n.Y").'.log', $log, FILE_APPEND);
?>
