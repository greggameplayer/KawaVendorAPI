<!DOCTYPE html>
<html lang=“fr”>
<head>
    <meta http-equiv=“Content-Type” content=“text/html; charset=UTF-8”/>
    <title></title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-color: #f0f0f0;
        }

        h3 {
            color: #333333;
            font-weight: bold;
        }

        p {
            color: #666666;
        }

        span {
            color: #ff6600;
            font-style: italic;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            padding: 20px;
        }

        td {
            text-align: center;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <td>
            <h3>Bonjour ${vendor}, bienvenue chez Paye ton kawa,</h3> <br>
            <p>Veuillez-vous authentifier grâce au QRCode ci-dessous :</p> <img src="data:image/png;base64,${qrCode}" alt=""> <br>
            <p>Vous pouvez aussi vous authentifier grâce au token ci-dessous :</p>
            <p>${token}</p> <br>
            <p>Si vous n’êtes pas à l’origine de cette demande, veuillez ignorer ce mail.</p>
            <p>Cordialement,</p>
            <p><span>L’équipe Paye ton kawa</span></p>
        </td>
    </tr>
</table>

</body>

</html>
