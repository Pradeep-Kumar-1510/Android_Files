package com.example.firstapp.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.firstapp.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Objects;

/**
 * @noinspection ALL
 */
public class CameraFragment extends Fragment {

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 1001;
    private static final int CAMERA_REQUEST = 1888;
    private static final int VIDEO_CAPTURE_REQUEST = 1889;
    private ImageView imageView;
    private File mediaDir;
    private Bitmap capturedBitmap;
    private Uri capturedVideoUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        imageView = view.findViewById(R.id.imageView1);
        Button photoButton = view.findViewById(R.id.button1);
        Button videoButton = view.findViewById(R.id.button2);

        photoButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
            } else {
                startCamera(false);
            }
        });

        videoButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
            } else {
                startCamera(true);
            }
        });

        mediaDir = requireActivity().getExternalFilesDir(null);

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera(false);
            } else {
                showToast("Camera permission denied");
            }
        }
    }

    private void startCamera(boolean isVideo) {
        Intent cameraIntent;
        if (isVideo) {
            cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        } else {
            cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        startActivityForResult(cameraIntent, isVideo ? VIDEO_CAPTURE_REQUEST : CAMERA_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                assert data != null;
                capturedBitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                imageView.setImageBitmap(capturedBitmap);
                showSaveOrCancelDialog(false);
            } else if (requestCode == VIDEO_CAPTURE_REQUEST) {
                assert data != null;
                capturedVideoUri = data.getData();
                if (capturedVideoUri != null) { //in if condition we are checking if the video is successfully captured or not
                    imageView.setImageURI(capturedVideoUri);
                    showSaveOrCancelDialog(true);
                }
            }
        }
    }

    private void showSaveOrCancelDialog(final boolean isVideo) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext());
        dialogBuilder.setMessage("Do you want to save the " + (isVideo ? "video" : "photo") + "?")
                .setCancelable(false)
                .setPositiveButton("Save", (dialog, which) -> {
                    if (isVideo) {
                        saveMediaToStorage(capturedVideoUri, true);
                    } else {
                        saveMediaToStorage(capturedBitmap, false);
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        AlertDialog alert = dialogBuilder.create();
        alert.setTitle("Save Media");
        alert.show();
    }

    private void saveMediaToStorage(Object media, boolean isVideo) {
        long timestamp = System.currentTimeMillis();
        String fileExtension = isVideo ? ".mp4" : ".jpg";
        String fileName = "media_" + timestamp + fileExtension;
        File mediaFile = new File(mediaDir, fileName);

        try {
            OutputStream outputStream = Files.newOutputStream(mediaFile.toPath());

            if (isVideo && media instanceof Uri) {
                Uri videoUri = (Uri) media;
                InputStream inputStream = requireActivity().getContentResolver().openInputStream(videoUri);

                byte[] buffer = new byte[1024];
                int bytesRead;

                while (true) {
                    assert inputStream != null;
                    if ((bytesRead = inputStream.read(buffer)) == -1) break;
                    outputStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
            } else if (!isVideo && media instanceof Bitmap) {
                Bitmap bitmap = (Bitmap) media;
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            } else {
                throw new IllegalArgumentException("Invalid media type");
            }

            outputStream.close();
            showToast((isVideo ? "Video" : "Photo") + " saved to " + mediaFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Failed to save " + (isVideo ? "video" : "photo"));
        }
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}

