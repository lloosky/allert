<?php
#$dump = print_r($_POST, true);
#$log = $dump.PHP_EOL;
#file_put_contents('./log_'.date("j-n-Y").'.log', $log, FILE_APPEND);
file_put_contents('./php.log', exec('git pull origin master').PHP_EOL, FILE_APPEND);
?>

